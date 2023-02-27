package com.golfzon.luuthaogolfzon.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

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
