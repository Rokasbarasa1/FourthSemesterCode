package com.example.version2myrecipe;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AddingRecipeTest {
    @Rule
    public ActivityTestRule<ActivityCreateRecipe> mAddRecipeActivityTestRule =
            new ActivityTestRule<ActivityCreateRecipe>(ActivityCreateRecipe.class);

    @Test
    public void createRecipe_correctDetails() throws Exception {
        //onView(withId(R.id.newRecipeName)).perform(a)
    }
}
