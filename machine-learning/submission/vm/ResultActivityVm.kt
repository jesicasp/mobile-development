package com.dicoding.asclepius.vm

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.database.PredictHistory
import com.dicoding.asclepius.repository.PredictHistoryRepository

class ResultActivityVm(application: Application) : ViewModel() {
    private val mPredictHistoryRepository: PredictHistoryRepository = PredictHistoryRepository(application)
    fun insert(predictHistory: PredictHistory) {
        mPredictHistoryRepository.insert(predictHistory)
    }
}