package com.golfzon.luuthaogolfzon.model

data class PhotosResponse(
    val photos: List<Photo>
)

data class Photo(
    val id: Int?,
    val photographer: String?,
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