package com.golfzon.luuthaogolfzon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.golfzon.luuthaogolfzon.model.CountriesService
import com.golfzon.luuthaogolfzon.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {
    val TAG = ListViewModel::class.java.simpleName
    private val countriesService = CountriesService()
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val disposable = CompositeDisposable()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        disposable.add(
            countriesService
                .getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(value: List<Country>?) {
                        loading.value = false
                        countryLoadError.value = false
                        countries.value = value
                    }

                    override fun onError(e: Throwable?) {
                        Log.e(TAG, "onError: ", e)
                        loading.value = false
                        countryLoadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}