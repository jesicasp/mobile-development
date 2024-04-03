package com.dicoding.roomnotesapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

//Data entities that represent tables in this app database.

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable

//awalnya adalah sebuah data model Note
//menjadi sebuah table dalam Room dengan menambahkan annotation @Entity
//Default dari nama tabel adalah sesuai dengan nama kelas tersebut. Kemudian variabel yang di dalamnya akan menjadi column dari tabel Note.
// Room uses the field names as column names in the database by default. If you want a column to have a different name, add the @ColumnInfo annotation to the field and set the name property.