package br.com.hussan.stonechallenge.ui.main

import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<FactsActivity> = ActivityScenarioRule(FactsActivity::class.java)

    @Before
    fun launchActivity() {
        ActivityScenario.launch(FactsActivity::class.java)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("br.com.hussan.stonechallenge", appContext.packageName)
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
//        onView(withId(R.id.rvFacts)).check(matches((isDisplayed())))
//        onView(withId(R.id.search))
//            .perform(click())
//
//        onView(withId(R.id.edtSearch))
//            .perform(typeText("car"), pressImeActionButton())
//
//        onView(withId(R.id.rvFacts)).check(matches((isDisplayed())))
//
//
//        onView(withText("Chuck Norris always knows the EXACT location of Carmen SanDiego."))
//            .check(matches(isDisplayed()))
//
//        onView(withText("Chuck Norris always knows the EXACT location of Carmen SanDiego."))
//            .check(matches(HintMatcher.withHint("Chuck Norris always knows the EXACT location of Carmen SanDiego.")))
//

    }
}

