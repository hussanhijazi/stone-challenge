package br.com.hussan.stonechallenge.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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
        bundle?.let {
            putExtras(bundle)
        }
        startActivity(this, options?.toBundle())
    }
}

inline fun <reified T : Activity> Activity.navigateForResult(
    codeRequest: Int,
    bundle: Bundle? = null
) {

    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let {
            putExtras(bundle)
        }
        startActivityForResult(this, codeRequest)
    }

//
//
//
//    val intent = Intent(this, T::class.java)
//    intent.apply {
//        bundle?.let {
//            putExtras(bundle)
//        }
//        startActivity(this, options?.toBundle())
//    }
}
