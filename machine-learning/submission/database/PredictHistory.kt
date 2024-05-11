package com.dicoding.asclepius.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class PredictHistory(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "label")
    var label: String? = null,

    @ColumnInfo(name = "score")
    var score: String? = null
):Parcelable