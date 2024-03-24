package com.dicoding.myreadwritefile

import android.content.Context

internal object FileHelper {
    fun writeToFile(fileModel: FileModel, context: Context){ //filemodel : objek kelas, context: akses penyimpanan internal
        context.openFileOutput(fileModel.filename, Context.MODE_PRIVATE).use{//mode private : file hanya dapat diakses oleh aplikasi yang membuatnya
            it.write(fileModel.data?.toByteArray())
        } //openFileOutput mengembalikan objek FileOutputStream, dan blok use memastikan bahwa objek tersebut akan ditutup secara otomatis setelah operasi penulisan selesai
    }

    fun readFromFile(context: Context, filename: String): FileModel{ //context adl konteks aplikasi yang diperlukan untuk mengakses penyimpanan internal
        val fileModel = FileModel()
        fileModel.filename = filename
        fileModel.data = context.openFileInput(filename).bufferedReader().useLines{lines -> //useLines digunakan untuk membaca konten file baris per baris
            lines.fold(""){some, text ->
                "$some$text\n"
            }
        }
        return fileModel
    }

}