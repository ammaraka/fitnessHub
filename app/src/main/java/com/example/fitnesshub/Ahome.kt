package com.example.fitnesshub

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class Ahome : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mlist = ArrayList<FitnessData>()
    private lateinit var adapter: fitness


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahome)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setSelectedItemId(R.id.bottom_home)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
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
                    startActivity(Intent(this, Aprofile::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    true // Return true to consume the event
                }
                else -> false // Return false to allow default selection
            }
        }
        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchHer)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        adapter = fitness(this, mlist)
        recyclerView.adapter=adapter


        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
              filterList(newText)
                return true

            }
        })



    }

    private fun filterList(query:String?){
        if(query !=null){
            val filteredList = ArrayList<FitnessData>()
            for(i in mlist){
                if(i.title.lowercase(Locale.ROOT).contains(query)){
                    filteredList.add(i)
                }

            }
            if(filteredList.isEmpty()){
                Toast.makeText(this,"No Data found",Toast.LENGTH_SHORT).show()
            }else{
                adapter.setFilteredList(filteredList)

            }
        }

    }

    private fun addDataToList(){
        mlist.add(FitnessData("UTHM Fitness Club", R.drawable.uthm_fitness, Aevent::class.java))
        mlist.add(FitnessData("BigBros Gym", R.drawable.big_bros, SignUp::class.java))
        mlist.add(FitnessData("ActiveLife Fitness", R.drawable.active_life, Aprofile::class.java))
        mlist.add(FitnessData("Dreamer's Gym", R.drawable.dreamers, Login::class.java))
        mlist.add(FitnessData("Muscle Monster", R.drawable.muscle_monster, Aprofile::class.java))




    }

}
