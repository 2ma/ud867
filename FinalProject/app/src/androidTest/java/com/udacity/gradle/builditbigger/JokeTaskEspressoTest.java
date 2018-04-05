package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import hu.am2.jokedisplay.JokeDisplayActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class JokeTaskEspressoTest {

    private static final String EXTRA_JOKE_QUESTION = "hu.am2.jokedisplay.extra.JOKE_QUESTION";
    private static final String EXTRA_JOKE_ANSWER = "hu.am2.jokedisplay.extra.JOKE_ANSWER";

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void jokeLoadingTest() {
        onView(withId(R.id.jokeButton)).perform(click());

        intended(allOf(
            hasComponent(JokeDisplayActivity.class.getName()),
            hasExtraWithKey(EXTRA_JOKE_ANSWER),
            hasExtraWithKey(EXTRA_JOKE_QUESTION)
        ));

        onView(withId(R.id.question)).check(matches(isDisplayed()));
    }
}
