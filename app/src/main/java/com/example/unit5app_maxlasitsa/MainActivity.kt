package com.example.unit5app_maxlasitsa

import DatabaseHelper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unit5app_maxlasitsa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var foodEntryAdapter: FoodEntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)
        foodEntryAdapter = FoodEntryAdapter(emptyList())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = foodEntryAdapter
        }

        binding.addEntryFab.setOnClickListener {
            // For demonstration, adding a static entry. In a real app, gather data from user input.
            addFoodEntry(FoodItem("Sample Food", 200, "2023-01-02"))
        }

        loadFoodEntries()
    }

    private fun addFoodEntry(foodEntry: FoodItem) {
        databaseHelper.addFoodEntry(foodEntry)
        loadFoodEntries() // Reload data to reflect the new entry
    }

    private fun loadFoodEntries() {
        val entries = databaseHelper.getAllFoodEntries()
        foodEntryAdapter.entries = entries
        foodEntryAdapter.notifyDataSetChanged()
    }
}