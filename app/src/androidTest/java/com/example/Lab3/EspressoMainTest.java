package com.example.Lab3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class EspressoMainTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void IsTimerOn() {
        onView(withId(R.id.time)).check(matches(isDisplayed()));
    }

    @Test
    public void IsYesButtonVisible(){
        for (int i=0; i<10; i++){
            onView(withId(R.id.yes_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("10")));

    }

    @Test
    public void IsNoButtonVisible(){
        onView(withId(R.id.no_btn)).check(matches(isClickable()));

        for (int i=0; i<10; i++){
            onView(withId(R.id.no_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("0")));

    }

    @Test
    public void IsQuestionVisible() {
        onView(withId(R.id.roolsView))
                .check(matches(isDisplayed()))
                .check(matches(withText("Чи співпадає назва кольору ліворуч з кольором текста праворуч?")));
    }

    @Test
    public void IsGameEndsInMinute() throws InterruptedException {
        int clickCount = 5;
        for (int i = 0; i < clickCount; i++){
            onView(withId(R.id.yes_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("5")));
        Thread.sleep(55 * 1000);
        int TIMEOUT = 10 * 1000;
        int CONDITION_CHECK_INTERVAL = 100;

        long startTime = System.currentTimeMillis();
        try {
            while(System.currentTimeMillis() - startTime < TIMEOUT) {
                onView(withId(R.id.roolsView)).check(matches(isDisplayed()));
                Thread.sleep(CONDITION_CHECK_INTERVAL);
            }
        } catch (NoMatchingViewException e){
            onView(withId(R.id.result_string)).check(matches(withText("Ваш результат:")));
            onView(withId(R.id.result)).check(matches(withText("5")));
        }
    }
}
