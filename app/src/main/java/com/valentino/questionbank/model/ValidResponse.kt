package com.valentino.questionbank.model

import com.squareup.moshi.Json

data class ValidResponse(@Json(name = "is_valid") var valid: Boolean)