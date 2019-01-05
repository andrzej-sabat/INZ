package com.example.andrz.quiz1;

import android.content.Context;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainActivityTest {

    private static final String FAKE_STRING = "Login was successful";



    @Test
    public void validate() {
        MainActivity mainActivity = new MainActivity();

        String result = mainActivity.validate("user","user");
        assertThat(result, is(FAKE_STRING));
    }

}
