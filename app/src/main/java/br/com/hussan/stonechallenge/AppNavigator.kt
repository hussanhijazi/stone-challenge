package br.com.hussan.stonechallenge

import android.app.Activity
import br.com.hussan.stonechallenge.extensions.navigate
import br.com.hussan.stonechallenge.ui.search.SearchActivity

@Suppress("TooManyFunctions")
class AppNavigator(val activity: Activity) {
    
    fun goToSearch() {
        activity.navigate<SearchActivity>()
    }
}
