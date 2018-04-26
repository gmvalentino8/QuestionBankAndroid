package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Folder(var fid : Int,
                  @Json(name = "folder") var name: String,
                  @Json(name = "description") var description: String) : Parcelable