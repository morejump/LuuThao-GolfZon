package com.golfzon.luuthaogolfzon.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import java.util.concurrent.TimeUnit


fun ImageView.loadUrl(url: String, @DrawableRes placeholder: Int? = null) {
    if (placeholder != null) {
        setImageResource(placeholder)
    }

    GlobalScope.launch(Dispatchers.Main) {
        val drawable = withContext(Dispatchers.IO) { loadDrawable(url) }
        setImageDrawable(drawable)
    }
}

private fun loadDrawable(url: String): Drawable? {
    return try {
        val inputStream = URL(url).openStream()
        Drawable.createFromStream(inputStream, null)
    } catch (e: Exception) {
        null
    }
}

fun SearchView.onTextChangeObservable(): Observable<String> {
    val subject = PublishSubject.create<String>()

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let {
                subject.onNext(newText)
            }
            return true
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                subject.onNext(query)
            }
            return true
        }
    })

    return subject
        .debounce(500, TimeUnit.MILLISECONDS)
        .filter { it.length >= 2 }
        .distinctUntilChanged()
}
