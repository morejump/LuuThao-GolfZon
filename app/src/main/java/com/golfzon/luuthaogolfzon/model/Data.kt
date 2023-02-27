package com.golfzon.luuthaogolfzon.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class PhotosResponse(
    val photos: List<Photo>
)

@Parcelize
data class Photo(
    val id: Int,
    val photographer: String,
    val src: PhotoSource
): Parcelable


@Parcelize
data class PhotoSource(
    val original: String,
    val large2x: String,
    val large: String,
    val medium: String,
    val small: String,
    val portrait: String,
    val landscape: String,
    val tiny: String
):Parcelable