package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
        @Json(name = "rating_1") var rating1: Int,
        @Json(name = "rating_2") var rating2: Int,
        @Json(name = "rating_3") var rating3: Int,
        @Json(name = "rating_4") var rating4: Int
) : Parcelable