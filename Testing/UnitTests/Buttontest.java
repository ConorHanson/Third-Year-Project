package com.example.rugstats;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

//testing the launch of the main activity, by identifying the text views on the page

public class Buttontest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule <MainActivity>(MainActivity.class);

    private MainActivity activity  = null;

    Instrumentation.ActivityMonitor m = getInstrumentation().addMonitor(SelectTeam.class.getName(),null,false);
    Instrumentation.ActivityMonitor m2 = getInstrumentation().addMonitor(CreateTeam.class.getName(),null,false);
    Instrumentation.ActivityMonitor m3 = getInstrumentation().addMonitor(SelectTeam.class.getName(),null,false);
    Instrumentation.ActivityMonitor m4 = getInstrumentation().addMonitor(Launcher.class.getName(),null,false);




    @Before
    public void setUp() throws Exception
    {
        activity = activityActivityTestRule.getActivity();

    }


    @Test
    public void testLaunchVS()
    {
        assertNotNull((activity.findViewById(R.id.viewstats)));
        onView(withId(R.id.viewstats)).perform(click());
        Activity SelectTeam = getInstrumentation().waitForMonitorWithTimeout(m,5000);
        assertNotNull(SelectTeam);
        SelectTeam.finish();
    }

    @Test
    public void TestLaunchCT()
    {
        assertNotNull((activity.findViewById(R.id.create_t)));
        onView(withId(R.id.viewstats)).perform(click());
        Activity SelectTeam = getInstrumentation().waitForMonitorWithTimeout(m2,5000);
        assertNotNull(SelectTeam);
        SelectTeam.finish();
    }
    @Test
    public void TestLaunchSM()
    {
        assertNotNull((activity.findViewById(R.id.start_match)));
        onView(withId(R.id.start_match)).perform(click());
        Activity SelectTeam = getInstrumentation().waitForMonitorWithTimeout(m3,5000);
        assertNotNull(SelectTeam);
        SelectTeam.finish();
    }
    @Test

    public void TestLaunchLG()
    {
        assertNotNull((activity.findViewById(R.id.logoutbtn)));
        onView(withId(R.id.logoutbtn)).perform(click());
        Activity SelectTeam = getInstrumentation().waitForMonitorWithTimeout(m4,5000);
        assertNotNull(SelectTeam);
        SelectTeam.finish();
    }


    @After
    public void tearDown() throws Exception
    {
        activity = null;

    }
}