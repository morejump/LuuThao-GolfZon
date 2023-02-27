package com.golfzon.luuthaogolfzon.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.golfzon.luuthaogolfzon.model.Photo

class ImagePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val images: MutableList<Photo>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = images.size

    override fun createFragment(position: Int): Fragment {
        return ImageFragment.newInstance(images[position].src.large)
    }

    fun removeImage(position: Int) {
        images.removeAt(position)
        notifyItemRemoved(position)
    }
}
