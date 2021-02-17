package ie.ucc.bis.is4447.claim_app;


import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ie.ucc.bis.is4447.claim_app.view.Dashboard;
import ie.ucc.bis.is4447.claim_app.view.UserLoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class LoginTesting {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ie.ucc.bis.is4447.claim_app", appContext.getPackageName());
    }
    @Rule
    public ActivityScenarioRule<UserLoginActivity> mActivityTestRule = new ActivityScenarioRule<>(UserLoginActivity.class);
    @Test
    public void simpleTest() {
        ViewInteraction editText = onView(
                withId(R.id.tvUser));
        editText.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(
                withId(R.id.tvUser));
        editText2.perform(scrollTo(), replaceText("daviskelly@techguys.com"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.tvPass)));
        editText3.perform(scrollTo(), replaceText("man_password"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.Login)));
        button.perform(click());

        System.out.println("Logged in");
    }
}