package br.com.hussan.stonechallenge.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.extensions.checkTextSize
import br.com.hussan.stonechallenge.ui.main.FactsActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FactsActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<FactsActivity> = ActivityTestRule(FactsActivity::class.java)

    @Before
    fun launchActivity() {
        ActivityScenario.launch(FactsActivity::class.java)
    }

    @Test
    fun checkTextSizeRecyclerView() {
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.rvFacts)

        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.edtSearch))
            .perform(typeText("travel"), pressImeActionButton())

        onView(withId(R.id.rvFacts)).check(matches((isDisplayed())))

        for (position in 0 until (recyclerView.adapter?.itemCount ?: 0)) {
            scrollAndCheckTextSize(R.id.rvFacts, position, R.id.txtFact)
        }

    }

    private fun scrollAndCheckTextSize(recyclerViewId: Int, position: Int, text: Int) {
        onView(withId(recyclerViewId)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(withId(recyclerViewId)).check(matches(checkTextSize(position, text)))
    }

    private fun checkTextSize(
        position: Int,
        targetViewId: Int
    ): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("The text size correct")
            }

            public override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val adapter = recyclerView.adapter
                var checkTextSize = false
                adapter?.let {
                    recyclerView.findViewHolderForAdapterPosition(position)?.run {
                        val targetView = itemView.findViewById<TextView>(targetViewId)
                        checkTextSize = targetView.checkTextSize()
                    }
                    return checkTextSize
                }
                return checkTextSize
            }
        }
    }
}
