package com.lxy.pink;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lxy.pink.ui.main.SplashActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by homelajiang on 2016/10/9 0009.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest {
    private static final String STRING_TO_BE_TYPED = "Peter";

    @Rule
    public ActivityTestRule<SplashActivity> splash =
            new ActivityTestRule<SplashActivity>(SplashActivity.class);

    @Test
    public void sayHello(){
//        onView(withId(R.id.editText)).perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard());
//        onView(withText("Say hello!")).perform(click()); //line 2
//
//        String expectedText = "Hello, " + STRING_TO_BE_TYPED + "!";
//        onView(withId(R.id.textView)).check(matches(withText(expectedText))); //line 3

    }
}
