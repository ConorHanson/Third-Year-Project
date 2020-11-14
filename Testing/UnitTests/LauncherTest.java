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

public class LauncherTest {

    @Rule
    public ActivityTestRule<Launcher> activityActivityTestRule =  new ActivityTestRule <Launcher>(Launcher.class);

    private Launcher activity  = null;

    Instrumentation.ActivityMonitor m = getInstrumentation().addMonitor(Login.class.getName(),null,false);
    Instrumentation.ActivityMonitor m2 = getInstrumentation().addMonitor(Register.class.getName(),null,false);




    @Before
    public void setUp() throws Exception
    {
        activity = activityActivityTestRule.getActivity();

    }


    @Test
    public void testLaunchReg()
    {
        assertNotNull((activity.findViewById(R.id.reg)));
        onView(withId(R.id.reg)).perform(click());
        Activity Register = getInstrumentation().waitForMonitorWithTimeout(m2,5000);
        assertNotNull(Register);
        Register.finish();
    }

    @Test
    public void TestLaunchLogin()
    {
        assertNotNull((activity.findViewById(R.id.loginbtn)));
        onView(withId(R.id.loginbtn)).perform(click());
        Activity Login = getInstrumentation().waitForMonitorWithTimeout(m,5000);
        assertNotNull(Login);
        Login.finish();
    }



    @After
    public void tearDown() throws Exception
    {
        activity = null;

    }
}