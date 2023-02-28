package com.golfzon.luuthaogolfzon.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.model.Photo
import kotlinx.android.synthetic.main.activity_detail_image.*

class DetailImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_image)

        val selectedPosition = intent.getIntExtra(MainActivity.POSITION_KEY, 0)
        val photos = intent.getSerializableExtra(MainActivity.PHOTOS_KEY) as ArrayList<Photo>

        val adapter = ImagePagerAdapter(supportFragmentManager, lifecycle, photos)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(selectedPosition, false)

        deleteButton.setOnClickListener {
            val currentPosition = viewPager.currentItem
            adapter.removeImage(currentPosition)
        }

    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}