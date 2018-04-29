package com.valentino.questionbank.model

import com.squareup.moshi.Json

data class StudentAnswer(var sid : Int,
                         @Json(name = "question_id") var qid: Int,
                         @Json(name = "rationale") var rationale : String,
                         @Json(name = "rating") var rating: Rating,
                         @Json(name = "initial_answer_id") var initialAnswer : Int,
                         @Json(name = "final_answer_id") var finalAnswer : Int)