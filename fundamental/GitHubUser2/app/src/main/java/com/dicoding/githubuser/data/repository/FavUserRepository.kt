package com.dicoding.githubuser.data.repository

import androidx.lifecycle.LiveData
import com.dicoding.githubuser.data.local.entity.FavUserDao
import com.dicoding.githubuser.data.local.entity.FavUserEntity
import com.dicoding.githubuser.data.utils.AppExecutors

class FavUserRepository private constructor(
    private val favoriteUserDao: FavUserDao,
    private val appExecutors: AppExecutors,
) {

    companion object {
        @Volatile
        private var instance: FavUserRepository? = null
        fun getInstance(
            favoriteUserDao: FavUserDao,
            appExecutors: AppExecutors,
        ): FavUserRepository =
            instance ?: synchronized(this) {
                instance ?: FavUserRepository(favoriteUserDao, appExecutors)
            }.also { instance = it }
    }

    fun getFavorite(username: String) = favoriteUserDao.getFavoriteUserByUsername(username)

    fun setFavorite(username: String, avatarUrl: String) {
        appExecutors.diskIO.execute {
            val favoriteUser = FavUserEntity(username, avatarUrl)
            favoriteUserDao.insert(favoriteUser)
        }
    }

    fun deleteFavorite(username: String, avatarUrl: String) {
        appExecutors.diskIO.execute {
            val favoriteUser = FavUserEntity(username, avatarUrl)
            favoriteUserDao.delete(favoriteUser)
        }
    }

    fun getFavoriteUser(): LiveData<List<FavUserEntity>> {
        return favoriteUserDao.getFavoriteUser()
    }
}

