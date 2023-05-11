package com.example.hogwarts_data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts_data.R
import com.example.hogwarts_data.databinding.AdapterHousesHogwartsBinding
import kotlin.random.Random

import com.example.hogwarts_data.model.HousesItem

class HogwartsAdapter : RecyclerView.Adapter<MainViewHolder>() {

        var houseList = mutableListOf<HousesItem>()
        var onItemClick: ((positionString: String) -> Unit)? = null
        fun setHouse(house: ArrayList<HousesItem>) {
            this.houseList = house.toMutableList()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterHousesHogwartsBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val colors = arrayOf(
                R.color.design_fab_stroke_top_outer_color,
                R.color.purple_200,
                R.color.purple_500,
                R.color.design_default_color_error,
                R.color.teal_200,
                R.color.yellow
            )
            val randomColor = colors[Random.nextInt(colors.size)]
            val house = houseList[position]
            holder.binding.name.text = house.name
            holder.binding.name.setBackgroundResource(randomColor)

            val id = houseList[position].id
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(id)
            }
        }

        override fun getItemCount(): Int {
            return houseList.size
        }
    }

    class MainViewHolder(val binding: AdapterHousesHogwartsBinding) : RecyclerView.ViewHolder(binding.root) {

    }
