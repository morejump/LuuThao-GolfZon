package com.golfzon.luuthaogolfzon.model

import com.golfzon.luuthaogolfzon.di.DaggerAPIComponent
import io.reactivex.Single
import javax.inject.Inject

class PexelsService {

    @Inject
    lateinit var api: PexelsAPI

    init {
        DaggerAPIComponent.create().inject(this)
    }

    fun searchPhotos(query: String, perPage: Int, page: Int): Single<PhotosResponse> {
        return api.searchPhotos(query, perPage, page)
    }
}