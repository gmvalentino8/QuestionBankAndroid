package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(
        @Json(name = "name") var content : String,
        @Json(name = "answer_id")var aid : Int? = null
) : Parcelable