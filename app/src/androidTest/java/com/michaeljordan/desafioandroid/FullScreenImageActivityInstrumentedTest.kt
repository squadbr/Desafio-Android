package com.michaeljordan.desafioandroid

import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.ui.view.DetailActivity
import com.michaeljordan.desafioandroid.ui.view.FullScreenImageActivity
import com.michaeljordan.desafioandroid.ui.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FullScreenImageActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule<FullScreenImageActivity>(FullScreenImageActivity::class.java, false, false)

    @Test
    fun movieDetailTest() {
        val intent = Intent()
        intent.putExtra("image_url", "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")
        intent.putExtra("title", "Title test")
        activityRule.launchActivity(intent)

        onView(withId(R.id.iv_fullscreen_poster)).check(matches(isDisplayed()))
        onView(withText("Title test")).check(matches(isDisplayed()))
    }

}
