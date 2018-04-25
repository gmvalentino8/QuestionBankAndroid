package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(@Json(name = "name") var name: String,
                @Json(name = "email") var email: String,
                @Json(name = "school_id") var school: Int,
                @Json(name = "body") var type: String): Parcelable