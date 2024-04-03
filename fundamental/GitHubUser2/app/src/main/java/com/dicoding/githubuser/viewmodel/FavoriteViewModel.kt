package com.dicoding.githubuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.data.local.entity.FavUserEntity
import com.dicoding.githubuser.data.repository.FavUserRepository

class FavoriteViewModel(private val userRepository: FavUserRepository) : ViewModel() {
    fun getFavoriteUser(): LiveData<List<FavUserEntity>> = userRepository.getFavoriteUser()

}