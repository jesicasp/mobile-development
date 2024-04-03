package com.dicoding.githubuser.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.dicoding.githubuser.R
import com.dicoding.githubuser.databinding.ActivityDetailUserBinding
import com.dicoding.githubuser.viewmodel.DetailUserViewModel
import com.dicoding.githubuser.viewmodel.UserViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel> {
        UserViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME)
        if (username != null) {
            detailUserViewModel.findDetailUser(username)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        if (username != null) {
            sectionsPagerAdapter.username = username
        }
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(DetailUserActivity.TAB_TITLES[position])
        }.attach()

        detailUserViewModel.username.observe(this) { username ->
            binding.tvDetailUsername.text = username
        }

        detailUserViewModel.name_detail.observe(this) { name ->
            binding.tvDetailName.text = name
        }

        detailUserViewModel.avatar_url.observe(this) { avatar_url ->
            Glide.with(this)
                .load(avatar_url)
                .circleCrop()
                .into(binding.ivDetailPhotoProfile)
        }

        detailUserViewModel.following.observe(this) { following ->
            binding.tvDetailFollowing.text = "$following Following"
        }

        detailUserViewModel.followers.observe(this) { followers ->
            binding.tvDetailFollowers.text = "$followers Followers"
        }

        detailUserViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        if (username != null) {
            detailUserViewModel.getFavorite(username).observe(this) { favorite ->
                val fabFavorite = binding.fab
                if (favorite == null) {
                    fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            fabFavorite.context,
                            R.drawable.ic_favorite_border
                        )
                    )
                } else {
                    fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            fabFavorite.context,
                            R.drawable.ic_favorited
                        )
                    )
                }

                binding.fab.setOnClickListener {
                    if (favorite == null) {
                        detailUserViewModel.setFavorite(
                            detailUserViewModel.username.value.toString(),
                            detailUserViewModel.avatar_url.value.toString()
                        )
                    } else {
                        detailUserViewModel.deleteFavorite(
                            detailUserViewModel.username.value.toString(),
                            detailUserViewModel.avatar_url.value.toString()
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val USERNAME = "username"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}