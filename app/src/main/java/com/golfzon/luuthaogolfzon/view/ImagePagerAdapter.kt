package com.golfzon.luuthaogolfzon.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.golfzon.luuthaogolfzon.model.Photo

class ImagePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val photos: MutableList<Photo>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pageIds= photos.map { it.hashCode().toLong() }

    override fun getItemCount() = photos.size

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(photos[position].src.large)
    }

    fun removeImage(position: Int) {
        photos.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemId(position: Int): Long {
        return photos[position].hashCode().toLong() // make sure notifyDataSetChanged() works
    }

    override fun containsItem(itemId: Long): Boolean {
        return pageIds.contains(itemId)
    }

}
