package com.dicoding.menggambarobject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.menggambarobject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        //binding.myImageView.setImageBitmap(bitmap)
        val canvas = Canvas(bitmap)

        val paint = Paint().apply {
            color = Color.BLUE
        }

        // Gambar lingkaran merah di tengah Bitmap
        canvas.drawCircle(250f, 250f, 100f, paint)

        // Tetapkan bitmap yang sudah diubah ke ImageView
        binding.myImageView.setImageBitmap(bitmap)

        binding.btnCanvas.setOnClickListener {
            val moveIntent = Intent(this@MainActivity, CanvasActivity::class.java)
            startActivity(moveIntent)
        }
    }


}