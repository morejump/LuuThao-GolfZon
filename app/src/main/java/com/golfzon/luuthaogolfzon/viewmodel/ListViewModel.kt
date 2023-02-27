package com.golfzon.luuthaogolfzon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.golfzon.luuthaogolfzon.model.PexelsService
import com.golfzon.luuthaogolfzon.model.Photo
import com.golfzon.luuthaogolfzon.model.PhotosResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {
    private val TAG = ListViewModel::class.java.simpleName
    private val pexelsService = PexelsService()
    private var CURRENT_PAGE_SEARCH = 0
    private val SIZE_PAGE_SEARCH  = 15
    val photos = MutableLiveData<List<Photo>>()
    val photoLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()



    fun fetchPhotos(query: String) {
        CURRENT_PAGE_SEARCH++
        loading.value = true
        disposable.add(
            pexelsService
                .searchPhotos(query, SIZE_PAGE_SEARCH, CURRENT_PAGE_SEARCH)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<PhotosResponse>() {
                    override fun onSuccess(value: PhotosResponse?) {
                        loading.value = false
                        photoLoadError.value = false
                        photos.value = value?.photos
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(TAG, "onError: ", e)
                        loading.value = false
                        photoLoadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}