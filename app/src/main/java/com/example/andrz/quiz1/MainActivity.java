package com.example.andrz.quiz1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.andrz.quiz1.Common.Common;
import com.example.andrz.quiz1.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import android.app.ProgressDialog;
import android.util.Log;



public class MainActivity extends AppCompatActivity {
    MaterialEditText edtNewUser,edtNewPassword,edtNewEmail;
    MaterialEditText edtUser;
    MaterialEditText edtPassword;
    String edtEmail;

    Button btnSignUp , btnSignIn;

    FirebaseDatabase database;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        database = FirebaseDatabase.getInstance();
        users  = database.getReference("Users");

        edtUser = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);


        btnSignIn = findViewById(R.id.btn2);
        btnSignUp = findViewById(R.id.btn1);

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                showSignUpDialog();
                

            }

        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(edtUser.getText().toString(),edtPassword.getText().toString());
            }
        });

    }






    protected void signIn(final String user, final String pwd) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user).exists()) {
                    if(!user.isEmpty()){
                        User login = dataSnapshot.child(user).getValue(User.class);
                        if(login.getPassword().equals(pwd))
                        {
                            Intent homeActivity = new Intent(MainActivity.this,Home.class);
                            Common.currentUser = login;
                            startActivity(homeActivity);
                            finish();

                        }
                        
                        else 
                            Toast.makeText(MainActivity.this, "Złe hasło", Toast.LENGTH_SHORT).show();

                            edtEmail = login.getEmail();
                            sendMessage(edtEmail);

                        
                    }
                    else {
                        Toast.makeText(MainActivity.this,"Podaj nazwe urzytkownika", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Urzytkownik nie istnieje", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public String validate(String userName, String password)
    {
        if(userName.equals("user") && password.equals("user"))
            return "Login was successful";
        else
            return "Invalid login!";
    }

    private void showSignUpDialog() {


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Zarejestruj się");
        alertDialog.setMessage("Wypełnij pola");

        LayoutInflater inflater = this.getLayoutInflater();
        View sign_up_layout = inflater.inflate(R.layout.sign_up_layout,null);

        edtNewUser = sign_up_layout.findViewById(R.id.edtNewUserName);
        edtNewPassword = sign_up_layout.findViewById(R.id.edtNewPassword);
        edtNewEmail = sign_up_layout.findViewById(R.id.edtNewEmail);

        alertDialog.setView(sign_up_layout);
       // alertDialog.setIcon(R.drawable.ic_account_box_black_24dp);

        alertDialog.setNegativeButton("ANULUJ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        alertDialog.setPositiveButton("POTWIERDŹ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final User user = new User(edtNewUser.getText().toString(), edtNewPassword.getText().toString(), edtNewEmail.getText().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(user.getUserName()).exists())
                            Toast.makeText(MainActivity.this, "Urzytkownik istnieje", Toast.LENGTH_SHORT).show();
                        else {
                            users.child(user.getUserName()).setValue(user);
                            Toast.makeText(MainActivity.this, "Urzytkownik zarejestrowany", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                dialogInterface.dismiss();
            }

        });
       alertDialog.show();
    }
    private void sendMessage(final String email) {
        final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        dialog.setTitle("Wysyłanie wiadomości");
        dialog.setMessage("Czekaj");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("quizapp1337@gmail.com", "856z2z7p");
                    sender.sendMail("QuizApp",
                            "Administrator informuje o nieudanej próbie logowania do twojego konta w naszej grze",
                            "quizapp1337@gmail.com",
                            email);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }
}

