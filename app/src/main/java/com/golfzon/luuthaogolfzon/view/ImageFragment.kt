package com.golfzon.luuthaogolfzon.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.utils.loadUrl

class ImageFragment : Fragment() {

    companion object {
        fun newInstance(imageUrl: String): ImageFragment {
            val args = Bundle().apply {
                putString("imageUrl", imageUrl)
            }
            val fragment = ImageFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        val imageView = view.findViewById<ImageView>(R.id.photo)
        arguments?.getString("imageUrl")?.let { imageUrl ->
            imageView.loadUrl(imageUrl)
        }
        return view
    }
}