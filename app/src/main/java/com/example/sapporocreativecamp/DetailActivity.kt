package com.example.sapporocreativecamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageUrl = intent.getStringExtra("imageUrl")
        val jname = intent.getStringExtra("jname")
        val ename = intent.getStringExtra("ename")

        val iv = findViewById<ImageView>(R.id.imageView2)
        iv?.let { Glide.with(this).load(imageUrl).into(it) }

        val jn = findViewById<TextView>(R.id.textView3)
        jn.text = jname

        val en = findViewById<TextView>(R.id.textView4)
        en.text = ename

        val backButton = findViewById<Button>(R.id.button)
        backButton.setOnClickListener { _ -> finish() }
    }
}