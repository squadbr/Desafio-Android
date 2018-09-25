package br.com.cemobile.moviescoreseeker

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.cemobile.moviescoreseeker.search.SearchMoviesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMoviesActivityTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(SearchMoviesActivity::class.java)

    private companion object {
        const val DELAY_2000_MS = 2000L
    }

    @Test
    fun searchBatmanMovieShouldShowMoviesList() {
        onView(withId(R.id.searchEdit))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("batman"))
                .check(matches(withText("batman")))

        await()

        val childAt0 = onView(withRecyclerView(R.id.moviesList).atPosition(0))
        childAt0.check(matches(hasDescendant(withText("Batman Begins"))))
        val childAt1 = onView(withRecyclerView(R.id.moviesList).atPosition(1))
        childAt1.check(matches(hasDescendant(withText("Batman v Superman: Dawn of Justice"))))
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    fun await(millis: Long = DELAY_2000_MS) = Thread.sleep(millis)

}