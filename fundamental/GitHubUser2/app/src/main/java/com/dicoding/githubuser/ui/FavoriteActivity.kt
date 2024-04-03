package com.dicoding.githubuser.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.R
import com.dicoding.githubuser.data.remote.response.ItemsItem
import com.dicoding.githubuser.databinding.ActivityFavoriteBinding
import com.dicoding.githubuser.viewmodel.FavoriteViewModel
import com.dicoding.githubuser.viewmodel.UserViewModelFactory

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favViewModel by viewModels<FavoriteViewModel> {
        UserViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_favorite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fav_act)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favViewModel.getFavoriteUser().observe(this) { users ->
            val items = arrayListOf<ItemsItem>()
            users.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl)
                items.add(item)
            }
            setListUser(items)
        }

        showRec()
    }

    private fun setListUser(users: List<ItemsItem>) {
        val adapter = ListUserAdapter()
        adapter.submitList(users)
        binding.rvFavUsers.adapter = adapter

    }

    private fun showRec() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvFavUsers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavUsers.addItemDecoration(itemDecoration)
    }
}