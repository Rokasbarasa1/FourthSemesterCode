package com.example.version2myrecipe.views;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.version2myrecipe.ActivityMain;
import com.example.version2myrecipe.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestAddRecipe {

    @Rule
    public ActivityTestRule<ActivityMain> mActivityTestRule = new ActivityTestRule<>(ActivityMain.class);

    @Test
    public void testAddRecipe() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.create_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.newRecipeName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.newRecipeName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0)));
        appCompatEditText2.perform(scrollTo(), replaceText("ROKAS"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.newRecipePrepTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatEditText3.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.newRecipeCookTime),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                2)));
        appCompatEditText4.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.newRecipeServingSize),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                3)));
        appCompatEditText5.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.ingredient_text),
                        childAtPosition(
                                allOf(withId(R.id.ingredientView),
                                        childAtPosition(
                                                withId(R.id.rv_new_recipe_ingredients),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("iNGREDIent 4500"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatMultiAutoCompleteTextView = onView(
                allOf(withId(R.id.newRecipeDescription),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatMultiAutoCompleteTextView.perform(scrollTo(), replaceText("Descript"), closeSoftKeyboard());

        ViewInteraction appCompatMultiAutoCompleteTextView2 = onView(
                allOf(withId(R.id.newRecipeDescription), withText("Descript"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatMultiAutoCompleteTextView2.perform(scrollTo(), click());

        pressBack();

        ViewInteraction appCompatMultiAutoCompleteTextView3 = onView(
                allOf(withId(R.id.newRecipeTags),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatMultiAutoCompleteTextView3.perform(scrollTo(), replaceText("Asian,Complicated,NEW"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton = onView(
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                9),
                        0));
        appCompatButton.perform(scrollTo(), click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
