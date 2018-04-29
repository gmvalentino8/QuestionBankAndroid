package com.valentino.questionbank.model

import com.squareup.moshi.Json

data class Session (
        @Json(name = "user_id") var uid: Int,
        @Json(name = "token") var token: String
)