package br.com.hussan.stonechallenge

import android.app.Activity
import br.com.hussan.stonechallenge.extensions.navigateForResult
import br.com.hussan.stonechallenge.ui.search.SearchActivity

class AppNavigator(private val activity: Activity) {

    fun goToSearch(requestCode: Int) {
        activity.navigateForResult<SearchActivity>(requestCode)
    }
}
