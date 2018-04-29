package com.valentino.questionbank.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(@Json(name = "folder_id") var fid: Int,
                    @Json(name = "question") var content : String,
                    @Json(name = "answers") var answers : List<Answer>,
                    @Json(name = "correct_answer_id") var correct : Int,
                    @Json(name = "solved") var solved : Boolean,
                    @Json(name = "tags") var tags : List<String>,
                    @Json(name = "question_id") var qid : Int? = null) : Parcelable