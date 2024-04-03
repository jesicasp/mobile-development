package com.dicoding.githubuser.data.local.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteUser: FavUserEntity)

    @Query("SELECT * FROM favorite_user WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavUserEntity>

    @Delete
    fun delete(favoriteUser: FavUserEntity)

    @Query("SELECT * FROM favorite_user")
    fun getFavoriteUser(): LiveData<List<FavUserEntity>>
}