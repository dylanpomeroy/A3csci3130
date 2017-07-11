package com.acme.a3csci3130;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Standard test to check App Context.
     * @throws Exception Standard ability of tests to throw exception.
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    /**
     * A helper method that clears and types in text to a given view.
     * @param id The id of the view to edit.
     * @param text The text to type in the view.
     */
    private void typeTextInInput(int id, String text){
        onView(withId(id)).perform(clearText());
        onView(withId(id)).perform(typeText(text));
    }

    /**
     * A test that verifies the create Business UI is working properly.
     * @throws Exception Standard ability of tests to throw exception.
     */
    @Test
    public void createBusinessUI() throws Exception {
        onView(withId(R.id.submitButton)).perform(click());

        typeTextInInput(R.id.txtNumber, "123456789");
        typeTextInInput(R.id.txtName, "Fishy McFishigan");
        typeTextInInput(R.id.txtPrimary, "Fisher");
        typeTextInInput(R.id.txtAddress, "123 McFishigan Lane, Halifish");
        typeTextInInput(R.id.txtProvince, "NS");

        onView(withId(R.id.submitButton)).perform(click());

        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    /**
     * A test that verifies the update Business UI is working properly.
     * @throws Exception Standard ability of tests to throw an exception.
     */
    @Test
    public void updateBusinessUI() throws Exception {
        IdlingResource idle = new ElapsedTimeIdlingResource(500);
        registerIdlingResources(idle);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        typeTextInInput(R.id.txtNumber, "123456789");
        typeTextInInput(R.id.txtName, "Fishy McFishigan");
        typeTextInInput(R.id.txtPrimary, "Fisher");
        typeTextInInput(R.id.txtAddress, "123 McFishigan Lane, Halifish");
        typeTextInInput(R.id.txtProvince, "NS");

        closeSoftKeyboard();

        onView(withId(R.id.updateButton)).perform(click());
        pressBack();

        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    /**
     * A test that verifies the erase Business UI is working properly.
     * @throws Exception Standard ability of tests to throw an exception.
     */
    @Test
    public void eraseBusinessUI() throws Exception {
        IdlingResource idle = new ElapsedTimeIdlingResource(500);
        registerIdlingResources(idle);

        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());

        onView(withId(R.id.deleteButton)).perform(click());
        pressBack();

        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }
}
