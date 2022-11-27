package com.example.Lab3;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class ResultTest {

    @Rule
    public ActivityScenarioRule<ResultActivity> activityRule;

    public ResultTest() {
        // predefine state
        Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(ctx, ResultActivity.class);
        intent.putExtra("result", "5");
        activityRule = new ActivityScenarioRule(intent);
    }

    @Ignore
    @Test
    public void testIntentMock() {

        Context context = Mockito.mock(Context.class);
        Intent intent = ResultActivity.createIntent(context, "5");
        Assert.assertNotNull(intent);

        Assert.assertEquals("5", intent.getStringExtra("result"));

    }

    @Test
    public void checkResultLabel() {
        onView(withId(R.id.result_string)).check(matches(withText("Ваш результат:")));
    }

    @Test
    public void checkResult () {
        onView(withId(R.id.result)).check(matches(withText("5")));
    }

    @Test
    public void checkPlayMoreBtn () {
        ViewInteraction playMoreBtn = onView(withId(R.id.play_btn));
        playMoreBtn.check(matches(withText(R.string.play_btn_text)));

        // After click on the button should be shown the main activity
        playMoreBtn.perform(click());
        onView(withText(R.string.rools_text)).check(matches(isDisplayed()));


    }

    @Test
    public void checkMainBtn () {
        ViewInteraction mainBtn = onView(withId(R.id.main_btn)).check(matches(withText(R.string.main_btn_text)));

        // After click on the button should be shown the start activity
        mainBtn.perform(click());
        onView(withId(R.id.start_btn)).check(matches(isDisplayed()));
    }

    @Test
    public void checkExitBtn () throws InterruptedException {
        ViewInteraction exitBtn = onView(withId(R.id.exit_btn)).check(matches(withText(R.string.exit_btn_text)));

        // After click on the button the app should be closed
        exitBtn.perform(click());
        Thread.sleep(500);
        assertEquals(activityRule.getScenario().getState(), Lifecycle.State.DESTROYED);
    }
}
