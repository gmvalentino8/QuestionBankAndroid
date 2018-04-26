package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rating(
        @Json(name = "confidence") var rating1: Int,
        @Json(name = "difficulty") var rating2: Int,
        @Json(name = "clarity") var rating3: Int,
        @Json(name = "preparedness") var rating4: Int
) : Parcelable