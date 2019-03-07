package br.com.hussan.stonechallenge.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.databinding.ListItemFactBinding
import br.com.hussan.stonechallenge.databinding.LytFactCategoriesBinding
import br.com.hussan.stonechallenge.domain.Fact

class FactsAdapter(private val clickListenerShare: (Fact) -> Unit) :
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
        val fact = facts[position]
        holder.binding.fact = fact

        holder.binding.imgFactShare.setOnClickListener {
            clickListenerShare.invoke(fact)
        }

        fact.category?.run {
            holder.binding.lytFactCategory.removeAllViews()
            val factCategoryBinding: LytFactCategoriesBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(holder.binding.root.context),
                    R.layout.lyt_fact_categories,
                    null,
                    false
                )

            forEach {
                factCategoryBinding.category = it
                holder.binding.lytFactCategory.addView(factCategoryBinding.root)
            }
        }
    }

    override fun getItemCount() = facts.size

}
