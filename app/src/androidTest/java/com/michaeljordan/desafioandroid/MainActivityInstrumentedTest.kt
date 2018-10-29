package com.michaeljordan.desafioandroid

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.michaeljordan.desafioandroid.ui.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun doSearchTest() {
        onView(withId(R.id.search_item)).perform(click())
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("something"))
        onView(withId(android.support.design.R.id.search_src_text))
            .perform(pressImeActionButton())
    }
}
