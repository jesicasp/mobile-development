package com.dicoding.githubuser.ui

import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.githubuser.R
import com.dicoding.githubuser.databinding.ActivityThemeSettingBinding
import com.dicoding.githubuser.viewmodel.SettingThemeViewModel
import com.dicoding.githubuser.viewmodel.UserViewModelFactory

class ThemeSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeSettingBinding
    private val settingThemeViewModel by viewModels<SettingThemeViewModel> {
        UserViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_theme_setting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.theme_setting)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityThemeSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val switchTheme = binding.switchTheme

        settingThemeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
                switchTheme.text = getString(R.string.light_mode)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
                switchTheme.text = getString(R.string.dark_mode)
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingThemeViewModel.saveThemeSetting(isChecked)
        }
    }
}