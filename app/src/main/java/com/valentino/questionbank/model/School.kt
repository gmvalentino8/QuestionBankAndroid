package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class School(
        @Json(name = "school_id") var sid: Int,
        @Json(name = "name") var name: String,
        @Json(name = "domain") var domain: String
) : Parcelable