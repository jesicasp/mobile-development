package com.dicoding.adoptapaw

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val imageProfile = findViewById<ImageView>(R.id.img_profile)
        val user_name = findViewById<TextView>(R.id.tv_user_name)
        val user_email = findViewById<TextView>(R.id.tv_user_email)

        user_name.setText("JESICA SANDITIA PUTRI")
        user_email.setText("jesicasanditia.sg@gmail.com")

        val btnBack = findViewById<ImageButton>(R.id.btn_back_profile)
        btnBack.setOnClickListener {
            val moveIntent = Intent(this@ProfileActivity, MainActivity::class.java)
            startActivity(moveIntent)
        }
    }
}