package com.example.citmoviedatabase_mf.ui.details

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.citmoviedatabase_mf.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest{

    private lateinit var scenario: ActivityScenario<DetailsActivity>

    @Before
    fun setUp(){

        scenario = launch(DetailsActivity::class.java)

        scenario.recreate()

       // scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun testingBackButton(){
        onView(withId(R.id.iv_backNavigationDetailsIcon)).perform(click())
        onView(withId(R.id.mainActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testShowLessShowMoreResource(){

        onView(withId(R.id.tv_showMore)).perform(click())
    }

    @Test
    fun testViewAllCasting(){

        onView(withId(R.id.tv_viewAllCastAndCrew)).perform(click())
        onView(withId(R.id.castingActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun testingViewAllPhotos(){

        onView(withId(R.id.tv_viewAllPhotos)).perform(click())
        onView(withId(R.id.scenesActivity)).check(matches(isDisplayed()))
    }
}