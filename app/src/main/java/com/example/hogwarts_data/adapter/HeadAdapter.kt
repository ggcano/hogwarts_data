package com.example.hogwarts_data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts_data.R
import com.example.hogwarts_data.databinding.AdapterTraitsBinding
import com.example.hogwarts_data.model.Head
import kotlin.random.Random

class TraitsAdapter () : RecyclerView.Adapter<TraitsViewHolder>() {

    var houseList = mutableListOf<Head>()
    fun setTraits(trait: List<Head>) {
        this.houseList = trait.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TraitsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterTraitsBinding.inflate(inflater, parent, false)
        return TraitsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TraitsViewHolder, position: Int) {
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
        holder.binding.nameTraits.text = house.lastName
        holder.binding.nameTraits.setBackgroundResource(randomColor)
        holder.binding.idTraits.text = house.id
    }



    override fun getItemCount(): Int {
        return houseList.size
    }
}

class TraitsViewHolder(val binding: AdapterTraitsBinding) : RecyclerView.ViewHolder(binding.root) {

}

