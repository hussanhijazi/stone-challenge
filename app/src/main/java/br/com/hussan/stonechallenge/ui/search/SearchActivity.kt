package br.com.hussan.stonechallenge.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.stonechallenge.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupSearchListener()
    }

    private fun setupSearchListener() {
        edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val returnIntent = Intent()
                returnIntent.putExtra("query", edtSearch.text.toString())
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
                true
            }
            false
        }
    }

}
