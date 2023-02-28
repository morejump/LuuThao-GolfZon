package com.golfzon.luuthaogolfzon.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.model.Photo
import kotlinx.android.synthetic.main.activity_detail_image.*


class DetailImageActivity : AppCompatActivity() {
    private lateinit var adapter: ImagePagerAdapter
    private lateinit var photos: ArrayList<Photo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_image)

        val selectedPosition = intent.getIntExtra(MainActivity.POSITION_KEY, 0)
        photos = intent.getSerializableExtra(MainActivity.PHOTOS_KEY) as ArrayList<Photo>
        adapter = ImagePagerAdapter(supportFragmentManager, lifecycle, photos)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(selectedPosition, false)

        deleteButton.setOnClickListener {
            val currentPosition = viewPager.currentItem
            adapter.removeImage(currentPosition)
        }

    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        returnIntent.putExtra(MainActivity.POSITION_KEY, viewPager.currentItem)
        returnIntent.putExtra(MainActivity.PHOTOS_KEY, photos)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}