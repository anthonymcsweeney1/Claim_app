package ie.ucc.bis.is4447.claim_app.view;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ie.ucc.bis.is4447.claim_app.R;
import ie.ucc.bis.is4447.claim_app.helper.ClaimAdapter;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ClaimTest{
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ie.ucc.bis.is4447.claim_app", appContext.getPackageName());
    }
    @Rule
    public ActivityScenarioRule<PendingClaims> mActivityTestRule = new ActivityScenarioRule<>(PendingClaims.class);
    @Test
    public void InvoiceTest() {

        ViewInteraction claimcard = onView(
                allOf(withId(R.id.textClaimNum), withText("CLA124"), isDisplayed()));
        claimcard.perform(click());

        ViewInteraction navdetails = onView(
                allOf(withId(R.id.item_comment)));
        navdetails.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.tvAddComment)));
        editText.perform(replaceText("Comment Test"), closeSoftKeyboard());

        ViewInteraction navinvoice = onView(
                allOf(withId(R.id.item_image)));
        navinvoice.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.button3)));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button4)));
        button2.perform(click());
    }
    }