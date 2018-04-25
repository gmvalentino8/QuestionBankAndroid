package com.valentino.questionbank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(var cid: Int,
                  var name: String,
                  var code: String,
                  var term: String) : Parcelable