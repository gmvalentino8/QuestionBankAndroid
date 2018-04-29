package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(@Json(name = "email") var email: String,
                @Json(name = "password") var password: String): Parcelable