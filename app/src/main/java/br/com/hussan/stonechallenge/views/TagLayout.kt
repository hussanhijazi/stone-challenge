package br.com.hussan.stonechallenge.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import br.com.hussan.stonechallenge.R
import br.com.hussan.stonechallenge.databinding.LytFactCategoriesBinding
import com.google.android.material.chip.ChipGroup

class TagLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChipGroup(context, attrs, defStyleAttr) {

    fun setData(data: List<String>) {
        removeAllViews()
        data.forEach {
            it.run {
                val factCategoryBinding: LytFactCategoriesBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.lyt_fact_categories,
                        null,
                        false
                    )
                factCategoryBinding.category = it
                addView(factCategoryBinding.root)
            }
        }
    }
}
