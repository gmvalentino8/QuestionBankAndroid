package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(@Json(name = "name") var name: String,
                  @Json(name = "class_code") var code: String,
                  @Json(name = "term") var term: String,
                  @Json(name = "id") var cid: Int? = null) : Parcelable