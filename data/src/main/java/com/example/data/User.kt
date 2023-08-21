package com.example.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: Int,
    var name: String,
    var email: String,
    var isFavourite: Boolean = false
): Parcelable