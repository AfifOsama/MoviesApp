package com.madman.moviesapp.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.madman.moviesapp.R
import com.madman.moviesapp.utils.DataDummy
import com.madman.moviesapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val dummyMovies = DataDummy.generateMovies()
    private val dummyTvshow = DataDummy.generateTVShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))

        onView(withId(R.id.img_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tb_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tb_favorite)).perform(click())
        onView(withId(R.id.tb_favorite)).check(matches(isChecked()))
    }

    @Test
    fun loadTvshow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvshow.size
            )
        )
    }

    @Test
    fun loadDetailTvshow() {
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))

        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))

        onView(withId(R.id.img_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.img_poster)).check(matches(isDisplayed()))

        onView(withId(R.id.tb_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tb_favorite)).perform(click())
        onView(withId(R.id.tb_favorite)).check(matches(isChecked()))
    }
}