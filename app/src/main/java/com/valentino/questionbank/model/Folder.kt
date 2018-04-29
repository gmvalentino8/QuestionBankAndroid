package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Folder(@Json(name = "class_id") var cid: Int,
                  @Json(name = "name") var name: String,
                  @Json(name = "description") var description: String,
                  @Json(name = "id") var fid : Int? = null) : Parcelable