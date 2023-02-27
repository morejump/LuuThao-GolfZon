package com.golfzon.luuthaogolfzon.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsAPI {

    @GET("search")
    fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Single<PhotosResponse>
}