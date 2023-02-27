package com.golfzon.luuthaogolfzon.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.model.Photo
import com.golfzon.luuthaogolfzon.utils.loadUrl
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoListAdapter(var photos: ArrayList<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    fun addPhotos(newPhotos: List<Photo>) {
        photos.addAll(newPhotos)
        notifyDataSetChanged()
    }

    fun clearAllData() {
        photos.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
    )

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    class PhotoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val author = view.author
        private val image = view.photo
        fun bind(photo: Photo) {
            author.text = photo.photographer
            image.loadUrl(photo.src.landscape)
        }

    }
}