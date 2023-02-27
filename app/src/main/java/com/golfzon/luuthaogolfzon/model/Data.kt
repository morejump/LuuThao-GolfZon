package com.golfzon.luuthaogolfzon.model

import com.google.gson.annotations.SerializedName

data class PhotosResponse(
    val photos: List<Photo>
)
data class Photo(
//    @SerializedName("id")
    val id: Int?,
//    @SerializedName("photographer")
    val photographer: String?,
//    @SerializedName("src")
    val src: PhotoSource
)

data class PhotoSource(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
)