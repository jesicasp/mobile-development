package com.dicoding.asclepius.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.database.PredictHistory
import com.dicoding.asclepius.database.PredictHistoryDao
import com.dicoding.asclepius.database.PredictHistoryDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PredictHistoryRepository(application: Application) {
    private val mPredictHistoryDao: PredictHistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = PredictHistoryDatabase.getDatabase(application)
        mPredictHistoryDao = db.predictHistoryDao()
    }
    fun getAllPredictHistory(): LiveData<List<PredictHistory>> = mPredictHistoryDao.getAllPredictHistory()
    fun insert(predictHistory: PredictHistory) {
        executorService.execute { mPredictHistoryDao.insert(predictHistory) }
    }
    fun delete(predictHistory: PredictHistory) {
        executorService.execute { mPredictHistoryDao.delete(predictHistory) }
    }
    fun update(predictHistory: PredictHistory) {
        executorService.execute { mPredictHistoryDao.update(predictHistory) }
    }

}