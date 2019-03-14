package br.com.hussan.stonechallenge.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.stonechallenge.AppNavigator
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.domain.Fact
import br.com.hussan.stonechallenge.extensions.add
import br.com.hussan.stonechallenge.extensions.hide
import br.com.hussan.stonechallenge.extensions.show
import br.com.hussan.stonechallenge.extensions.snack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_facts.*
import kotlinx.android.synthetic.main.lyt_loading.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class FactsActivity : AppCompatActivity() {

    private val viewModel: FactsViewModel by viewModel()
    private val factsAdapter by lazy { FactsAdapter(::shareFact) }
    private val navigator: AppNavigator by inject { parametersOf(this@FactsActivity) }
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val SEARCH_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        setupRecyclerViewFacts()
        getCategories()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                navigator.goToSearch(SEARCH_REQUEST)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SEARCH_REQUEST) {
                data?.getStringExtra("query")?.let {
                    getFacts(it)
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            factsAdapter.setItems(viewModel.getResultFacts().value ?: return)
        }
    }

    private fun getFacts(query: String) {
        viewModel.getFacts(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { showLoading(true) }
            .doOnComplete { showLoading(false) }
            .doOnError { showLoading(false) }
            .subscribe({
                factsAdapter.setItems(it)
            }, ::showError)
            .add(compositeDisposable)
    }

    private fun getCategories() {
        viewModel.getCategtories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .add(compositeDisposable)
    }

    private fun showError(error: Throwable) {
        lytRoot.snack(R.string.error_message)
    }

    private fun showLoading(show: Boolean) {
        if (show)
            progressBar.show()
        else
            progressBar.hide()
    }

    private fun shareFact(fact: Fact) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, fact.value)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.send_to)))
    }

    private fun setupRecyclerViewFacts() {
        rvFacts.run {
            setHasFixedSize(true)
            adapter = factsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
