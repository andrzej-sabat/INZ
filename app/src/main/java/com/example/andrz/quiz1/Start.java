package com.example.andrz.quiz1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.andrz.quiz1.Common.Common;
import com.example.andrz.quiz1.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Start extends AppCompatActivity {

    Bundle btnPlay;

    FirebaseDatabase database;
    DatabaseReference questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        database = FirebaseDatabase.getInstance();
        questions = database.getReference("Questions");
    }

    private void loadQuestion(String categoryId){

        questions.orderByChild("categoryId").equalTo(categoryId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Question ques = postSnapshot.getValue(Question.class);
                    Common.questionList.add(ques);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }
    }
    }

