package com.dicoding.adoptapaw

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.adoptapaw.DetailPet
import com.dicoding.adoptapaw.ListPetAdapter
import com.dicoding.adoptapaw.Pet
import com.dicoding.adoptapaw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var rvPets: RecyclerView
    private val list = ArrayList<Pet>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        rvPets = findViewById(R.id.rv_pets)
        rvPets.setHasFixedSize(true)

        list.addAll(getListPets())
        showRecyclerList()

        val profile = findViewById<ImageButton>(R.id.about_page)
        profile.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(moveIntent)
        }
    }

    private fun getListPets(): ArrayList<Pet> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataAddress = resources.getStringArray(R.array.data_address)
        val dataContact = resources.getStringArray(R.array.data_cp)
        val listPet = ArrayList<Pet>()
        for (i in dataName.indices) {
            val pet =
                Pet(
                    dataName[i],
                    dataDescription[i],
                    dataPhoto.getResourceId(i, -1),
                    dataAddress[i],
                    dataContact[i]
                )
            listPet.add(pet)
        }
        return listPet
    }

    private fun showRecyclerList() {
        rvPets.layoutManager = LinearLayoutManager(this)
        val listPetAdapter = ListPetAdapter(list)
        rvPets.adapter = listPetAdapter

        listPetAdapter.setOnItemClickCallback(object : ListPetAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Pet) {
                //showSelectedPet(data)
                val intent = Intent(this@MainActivity, DetailPet::class.java)
                intent.putExtra("PET_NAME", data.name)
                intent.putExtra("PET_DESCRIPTION", data.description)
                intent.putExtra("PET_ADDRESS", data.address)
                intent.putExtra("PET_PHOTO_RES_ID", data.photo)
                intent.putExtra("PET_CONTACT", data.cp)
                startActivity(intent)
            }
        })
    }


}
