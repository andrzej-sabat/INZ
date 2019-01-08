package com.example.andrz.quiz1;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.DaggerBaseLayerComponent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class HomeTest {
    @Rule
public ActivityTestRule<Home> testRule =
        new ActivityTestRule<>(Home.class);
/*
    @Before
    public void init(){
        testRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }
*/
    @Test
    public void test1() {
        onView(withId(R.id.action_category)).perform(click());
        onView(allOf(withId(R.id.category),withEffectiveVisibility(VISIBLE))).
                check(matches(isDisplayed()));
    }
    @Test
    public void test2() {
        onView(withId(R.id.action_ranking)).perform(click());
        onView(allOf(withId(R.id.ranking),withEffectiveVisibility(VISIBLE))).
                check(matches(isDisplayed()));
    }
    }
