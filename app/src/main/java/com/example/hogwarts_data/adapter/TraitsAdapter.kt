package com.example.hogwarts_data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts_data.R
import com.example.hogwarts_data.databinding.AdapterHousesHogwartsBinding
import com.example.hogwarts_data.databinding.AdapterTraitsBinding
import com.example.hogwarts_data.model.HousesItem
import com.example.hogwarts_data.model.Trait
import kotlin.random.Random

class TraitsAdapter : RecyclerView.Adapter<TraitsViewHolder>() {

    var houseList = mutableListOf<Trait>()
    fun setTraits(trait: List<Trait>) {
        this.houseList = trait.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTraitsBinding.inflate(inflater, parent, false)
        return TraitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TraitsViewHolder, position: Int) {

        val house = houseList[position]
        holder.binding.nameTraits.text = house.name
        holder.binding.nameTraits.text = house.id

    }



    override fun getItemCount(): Int {
        return houseList.size
    }
}

class TraitsViewHolder(val binding: AdapterTraitsBinding) : RecyclerView.ViewHolder(binding.root) {

}

