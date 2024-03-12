package com.dicoding.adoptapaw

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pet(
    val name: String,
    val description: String,
    val photo: Int,
    val address : String,
    val cp : String
) : Parcelable

