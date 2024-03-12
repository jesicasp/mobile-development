package com.dicoding.adoptapaw

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_pet)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val detail = findViewById<TextView>(R.id.tv_description)
        val address = findViewById<TextView>(R.id.tv_address)


        detail.setText("Detail :")
        address.setText("Alamat :")



        val btnBack = findViewById<ImageButton>(R.id.btn_back_menu)
        btnBack.setOnClickListener {
            val moveIntent = Intent(this@DetailPet, MainActivity::class.java)
            startActivity(moveIntent)
        }


        val petName = intent.getStringExtra("PET_NAME")
        val petDescription = intent.getStringExtra("PET_DESCRIPTION")
        val petAddress = intent.getStringExtra("PET_ADDRESS")
        val petContact = intent.getStringExtra("PET_CONTACT")
        val petPhotoResId = intent.getIntExtra("PET_PHOTO_RES_ID", R.drawable.bengal_cat)

        val imgPhoto = findViewById<ImageView>(R.id.img_pet_detail)
        val tvPetName = findViewById<TextView>(R.id.tv_pet_name_detail)
        val tvDescription: TextView = findViewById(R.id.tv_pet_description_detail)
        val tvAddress: TextView = findViewById(R.id.tv_pet_address_detail)

        val btnCp = findViewById<Button>(R.id.btn_cp)
        btnCp.setOnClickListener {
            val phoneNumber = petContact
            val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(dialPhoneIntent)
        }

        imgPhoto.setImageResource(petPhotoResId)
        tvPetName.text = petName
        tvDescription.text = petDescription
        tvAddress.text = petAddress


    }
}