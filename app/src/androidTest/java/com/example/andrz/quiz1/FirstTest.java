package com.example.andrz.quiz1;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;



import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;

import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FirstTest {
    private String userName;
    private String userPass;
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
            userName = "1";
            userPass = "1";
    }

    @Test
    public void test1ChatId() {
        onView(withText("Zaloguj się")).check(matches(isDisplayed()));
    }

    @Test
    public void dialogTest(){
        onView(withId(R.id.btn1))
                .perform(click());
        onView(withText("Wypełnij pola"))
                .check(matches(isDisplayed()));
        onView(withText("ANULUJ"))
                .perform(click());
        onView(withText("Zaloguj się"))
                .check(matches(isDisplayed()));

    }


    @Test
    public void sign() {
        onView(withId(R.id.edtUserName))
                .perform(replaceText("1"));
        onView(withId(R.id.edtPassword))
                .perform(replaceText("1"));
        onView(withId(R.id.btn2))
                .perform(click());
        // onView(withId(R.id.navigation)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        //After Test case Execution
    }
}
