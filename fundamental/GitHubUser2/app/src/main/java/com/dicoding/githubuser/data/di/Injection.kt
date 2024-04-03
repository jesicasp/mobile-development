package com.dicoding.githubuser.data.di

import android.content.Context
import com.dicoding.githubuser.data.local.entity.FavUserRoomDatabase
import com.dicoding.githubuser.data.repository.FavUserRepository
import com.dicoding.githubuser.data.utils.AppExecutors


object Injection {
    fun provideRepository(context: Context): FavUserRepository {
        val database = FavUserRoomDatabase.getDatabase(context)
        val dao = database.favUserDao()
        val appExecutors = AppExecutors()
        return FavUserRepository.getInstance(dao, appExecutors)
    }
}