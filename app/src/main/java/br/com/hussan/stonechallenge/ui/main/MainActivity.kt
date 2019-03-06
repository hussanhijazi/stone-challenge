package br.com.hussan.stonechallenge.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.hussan.stonechallenge.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerViewFacts()
    }

    private fun setupRecyclerViewFacts() {
        rvFacts.setHasFixedSize(true)
    }
}
