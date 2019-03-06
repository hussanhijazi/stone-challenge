package br.com.hussan.stonechallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.databinding.ListItemFactBinding
import br.com.hussan.stonechallenge.domain.Fact

class FactsAdapter :
    RecyclerView.Adapter<FactsAdapter.FactViewHolder>() {

    private var facts: List<Fact> = listOf()

    inner class FactViewHolder(val binding: ListItemFactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding: ListItemFactBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_fact, parent, false)
        return FactViewHolder(binding)
    }

    fun setItems(items: List<Fact>) {
        facts = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.binding.fact = facts[position]
    }

    override fun getItemCount() = facts.size

}
