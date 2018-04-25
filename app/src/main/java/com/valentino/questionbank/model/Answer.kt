package com.valentino.questionbank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(var aid : Int,
                    var content : String) : Parcelable