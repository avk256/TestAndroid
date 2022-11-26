package com.example.Lab3;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EspressoMainTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void A_checkTimeVisible() {
        onView(withId(R.id.time)).check(matches(isDisplayed()));
    }

    @Test
    public void B_checkTestModeButtonYes(){
        for (int i=0; i<10; i++){
            onView(withId(R.id.yes_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("10")));
    }

    @Test
    public void C_checkTestModeButtonNo(){
        onView(withId(R.id.no_btn)).check(matches(isClickable()));

        for (int i=0; i<10; i++){
            onView(withId(R.id.no_btn)).perform(click());
        }
        onView(withId(R.id.points)).check(matches(withText("0")));
    }

    @Test
    public void D_checkQuestion() {
        onView(withId(R.id.roolsView))
                .check(matches(isDisplayed()))
                .check(matches(withText("Чи співпадає назва кольору ліворуч з кольором текста праворуч?")));
    }

    @Test
    public void Z_checkMoveToResultActivityAfterMinute() throws InterruptedException {
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
