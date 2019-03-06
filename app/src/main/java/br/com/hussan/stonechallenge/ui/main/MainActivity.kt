package br.com.hussan.stonechallenge.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.extensions.add
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: FactsViewModel by viewModel()
    private val factsAdapter = FactsAdapter()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerViewFacts()

        viewModel.getFacts("car")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ factsAdapter.setItems(it) }, { showError() })
            .add(compositeDisposable)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                // TODO go to search Activity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
