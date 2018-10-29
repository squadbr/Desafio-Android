package com.michaeljordan.desafioandroid

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.michaeljordan.desafioandroid.model.Movie
import com.michaeljordan.desafioandroid.ui.view.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java, false, false)

    @Test
    fun movieDetailTest() {
        val intent = Intent()
        intent.putExtra("movie", mockObject())
        activityRule.launchActivity(intent)

        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_plot_label)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_plot_detail)).check(matches(isDisplayed()))
        onView(withText(mockObject().imdbRating)).check(matches(isDisplayed()))
    }

    private fun mockObject(): Movie {
        return Movie("Movie Test", "imdb0000", "N/A", "0.0", "test")
    }
}
