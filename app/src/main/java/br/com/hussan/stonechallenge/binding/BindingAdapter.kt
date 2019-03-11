package br.com.hussan.stonechallenge.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.hussan.stonechallenge.extensions.spToPx

@BindingAdapter("setFontSize")
fun TextView.setFontSize(text: String?) {
    textSize = if (text?.length ?: 0 > 80)
        16F.spToPx(context)
    else
        24F.spToPx(context)
}
