package br.com.hussan.stonechallenge

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`


object HintMatcher {

    internal fun withHint(substring: String): Matcher<View> {
        return withHint(`is`(substring))
    }

    internal fun withHint(stringMatcher: Matcher<String>): Matcher<View> {
        checkNotNull(stringMatcher)
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {

            public override fun matchesSafely(view: TextView): Boolean {
                print("h2 " + view.textSize.toString())
                Log.d(
                    "h2 ",
                    (view.textSize / view.context.resources.getDisplayMetrics().scaledDensity).toString()
                )
                val hint = view.textSize > 3000
                return hint /*!= null && stringMatcher.matches(hint.toString())*/
            }

            override fun describeTo(description: Description) {
                description.appendText("with hint: ")
                stringMatcher.describeTo(description)
            }
        }
    }
}
