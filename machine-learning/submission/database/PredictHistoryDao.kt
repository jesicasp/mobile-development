package com.dicoding.asclepius.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PredictHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(predictHistory: PredictHistory)

    @Update
    fun update(predictHistory: PredictHistory)

    @Delete
    fun delete(predictHistory: PredictHistory)

    @Query("SELECT * from predicthistory ORDER BY id ASC")
    fun getAllPredictHistory(): LiveData<List<PredictHistory>>
}
