package com.dicoding.myapplication1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BarVolume : AppCompatActivity() {

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    lateinit var edtPanjang: EditText
    lateinit var edtLebar: EditText
    lateinit var edtTinggi: EditText
    lateinit var btnHitung: Button
    lateinit var hasil: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bar_volume)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtPanjang = findViewById(R.id.edt_panjang)
        edtLebar = findViewById(R.id.edt_lebar)
        edtTinggi = findViewById(R.id.edt_tinggi)
        btnHitung = findViewById(R.id.btn_hitung)
        hasil = findViewById(R.id.tv_hasil)

        btnHitung.setOnClickListener {
            val panjang = edtPanjang.text.toString().trim()
            val lebar = edtLebar.text.toString().trim()
            val tinggi = edtTinggi.text.toString().trim()

            val inputs = listOf(
                Pair(edtPanjang, "Panjang"),
                Pair(edtLebar, "Lebar"),
                Pair(edtTinggi, "Tinggi")
            )

            var isEmptyFields = false

            for ((inputfield, fieldname) in inputs) {
                val input = inputfield.text
                if (input.isEmpty()) {
                    isEmptyFields = true
                    inputfield.error = "Field $fieldname tidak boleh kosong"
                }
            }

            if (!isEmptyFields) {
                val volume = panjang.toDouble() * lebar.toDouble() * tinggi.toDouble()
                hasil.text = volume.toString()
            }
        }

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            hasil.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, hasil.text.toString())
    }
}