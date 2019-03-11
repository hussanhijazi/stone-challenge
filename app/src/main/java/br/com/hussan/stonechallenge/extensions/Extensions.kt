package br.com.hussan.stonechallenge.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Int.pxToSp(): Float = this / Resources.getSystem().displayMetrics.scaledDensity
fun Float.spToPx(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this, context.resources.displayMetrics)

fun Disposable.add(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

inline fun <reified T : Activity> Activity.navigate(
    bundle: Bundle? = null,
    options: ActivityOptionsCompat? = null
) {
    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let { putExtras(bundle) }
        startActivity(this, options?.toBundle())
    }
}

inline fun <reified T : Activity> Activity.navigateForResult(
    codeRequest: Int,
    bundle: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let { putExtras(bundle) }
        startActivityForResult(this, codeRequest)
    }
}
