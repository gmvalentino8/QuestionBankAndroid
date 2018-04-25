package com.valentino.questionbank.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Folder(var fid : Int,
                  var name: String,
                  var description: String) : Parcelable