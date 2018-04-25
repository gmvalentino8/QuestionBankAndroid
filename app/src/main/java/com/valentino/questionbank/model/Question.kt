package com.valentino.questionbank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(var qid : Int,
                    var content : String,
                    var answers : List<Answer>,
                    var correct : Int,
                    var tags : List<String>) : Parcelable