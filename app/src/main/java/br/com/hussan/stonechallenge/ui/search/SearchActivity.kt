package br.com.hussan.stonechallenge.ui.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.extensions.add
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private val viewModel: SearchViewModel by viewModel()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupSearchListener()
        getCategories()
    }

    private fun getCategories() {
        viewModel.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                lytCategories.setData(it.map { it.name ?: "" })
            }, {}, {})
            .add(compositeDisposable)
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
