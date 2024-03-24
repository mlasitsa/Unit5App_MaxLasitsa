package com.example.unit5app_maxlasitsa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.unit5app_maxlasitsa.databinding.ItemFoodEntryBinding

class FoodEntryAdapter(var entries: List<FoodItem>) : RecyclerView.Adapter<FoodEntryAdapter.EntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val binding = ItemFoodEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EntryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.binding.foodName.text = entry.name
        holder.binding.calories.text = "${entry.calories} Cal"
    }

    override fun getItemCount(): Int = entries.size

    class EntryViewHolder(val binding: ItemFoodEntryBinding) : RecyclerView.ViewHolder(binding.root)
}