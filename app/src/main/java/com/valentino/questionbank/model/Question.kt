package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(var qid : Int,
                    @Json(name = "question") var content : String,
                    @Json(name = "answers") var answers : List<Answer>,
                    @Json(name = "correct_answer_id") var correct : Int,
                    var tags : List<String>) : Parcelable