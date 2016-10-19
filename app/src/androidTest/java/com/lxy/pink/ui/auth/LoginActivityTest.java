package com.lxy.pink.ui.auth;


import android.support.test.rule.ActivityTestRule;

import com.lxy.pink.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by homelajiang on 2016/10/19 0019.
 */
public class LoginActivityTest {
    String tempName = "homelajiang";

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule =
            new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void signIn() throws Exception {
        onView(withId(R.id.username))
                .perform(typeText("homelajiang"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("111111"), closeSoftKeyboard());
        onView(withId(R.id.signIn))
                .perform(click());

    }

    @Test
    public void signUp() throws Exception {

    }

}