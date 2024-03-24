package com.dicoding.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubuser.data.response.DetailUserResponse
import com.dicoding.githubuser.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _name_detail = MutableLiveData<String>()
    val name_detail: LiveData<String> = _name_detail

    private val _avatar_url = MutableLiveData<String>()
    val avatar_url: LiveData<String> = _avatar_url

    private val _following = MutableLiveData<Int>()
    val following: LiveData<Int> = _following

    private val _followers = MutableLiveData<Int>()
    val followers: LiveData<Int> = _followers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val  TAG = "DetailUserViewModel"
    }

    fun findDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _username.value = response.body()?.login
                    _name_detail.value = response.body()?.name
                    _avatar_url.value = response.body()?.avatarUrl
                    _following.value = response.body()?.following
                    _followers.value = response.body()?.followers
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}