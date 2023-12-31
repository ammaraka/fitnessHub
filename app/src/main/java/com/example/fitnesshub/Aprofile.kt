package com.example.fitnesshub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Aprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aprofile)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setSelectedItemId(R.id.bottom_profile)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    startActivity(Intent(this, Ahome::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // Handle home item selection
                    true // Return true to consume the event
                }
                R.id.bottom_event -> {
                    // Start the Event activity with animation
                    startActivity(Intent(this, Aevent::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true // Return true to consume the event
                }
                R.id.bottom_profile -> {
                    // Start the Profile activity with animation

                    true // Return true to consume the event
                }
                else -> false // Return false to allow default selection
            }
        }    }
}