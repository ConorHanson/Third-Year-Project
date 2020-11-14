package com.example.rugstats;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

//testing the launch of the main activity, by identifying the text views on the page

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule <MainActivity>(MainActivity.class);

    private MainActivity activity  = null;




    @Before
    public void setUp() throws Exception
    {
        activity = activityActivityTestRule.getActivity();

    }


    @Test
    public void testLaunch()
    {
        View view = activity.findViewById(R.id.start_match);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception
    {
        activity = null;

    }
}