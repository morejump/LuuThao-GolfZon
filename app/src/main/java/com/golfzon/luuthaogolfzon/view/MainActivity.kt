package com.golfzon.luuthaogolfzon.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.golfzon.luuthaogolfzon.R
import com.golfzon.luuthaogolfzon.model.Photo
import com.golfzon.luuthaogolfzon.utils.onTextChangeObservable
import com.golfzon.luuthaogolfzon.viewmodel.ListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnItemClickListener {
    val TAG = MainActivity::class.java.simpleName
    var isLoading = false
    val REQUEST_CODE = 123

    companion object {
        val PHOTOS_KEY = "PHOTOS_KEY"
        val POSITION_KEY = "POSITION_KEY"
    }

    lateinit var viewModel: ListViewModel
    private val photoListAdapter = PhotoListAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        photosList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = photoListAdapter
        }
        observeViewModel()
        viewListener()
    }

    private fun viewListener() {
        searchView.onTextChangeObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                photoListAdapter.clearAllData()
                viewModel.fetchPhotos(it)

            })

        photosList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading && recyclerView.layoutManager != null && (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == photoListAdapter.itemCount - 1) {
                    isLoading = true
                    loadMoreData()
                }
            }
        })
    }

    private fun loadMoreData() {
        Log.i(TAG, "Load more data")
        viewModel.fetchPhotos(searchView.query.toString())
        isLoading = false
    }

    private fun observeViewModel() {
        viewModel.photos.observe(this, Observer { photos ->
            Log.i(TAG, "Receive a new data with a size: " + photos.size)
            photos?.let {
                photoListAdapter.addPhotos(photos)
            }
        })
        viewModel.loading.observe(this, Observer { done ->
            loading_view.visibility = if (done) View.VISIBLE else View.GONE

        })
        viewModel.photoLoadError.observe(this, Observer { isError ->
            listError.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailImageActivity::class.java)
        intent.putExtra(POSITION_KEY, position)
        intent.putExtra(PHOTOS_KEY, photoListAdapter.getAllPhotos())
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            // Handle data from detail image activity
            val selectedPosition = data?.getIntExtra(POSITION_KEY, 0)
            val photos = data?.getSerializableExtra(PHOTOS_KEY) as ArrayList<Photo>

            // Update data to the recyclerview
            photoListAdapter.clearAllData()
            photoListAdapter.addPhotos(photos)

            // Scroll to the last image view in the detail image activity
            val layoutManager = photosList.layoutManager as LinearLayoutManager
            selectedPosition?.let { layoutManager.scrollToPositionWithOffset(it, 0) }
        }
    }

}