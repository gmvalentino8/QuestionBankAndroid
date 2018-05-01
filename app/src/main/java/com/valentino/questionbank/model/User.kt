package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(@Json(name = "name") var name: String,
                @Json(name = "email") var email: String,
                @Json(name = "school_id") var schoolId: Int,
                @Json(name = "school_name") var school: String,
                @Json(name = "type") var type: String): Parcelable