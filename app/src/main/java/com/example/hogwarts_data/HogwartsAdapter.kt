package com.example.hogwarts_data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import com.example.hogwarts_data.databinding.AdapterMovieBinding
import com.example.hogwarts_data.model.HousesItem

class HogwartsAdapter : RecyclerView.Adapter<MainViewHolder>() {

        var movieList = mutableListOf<HousesItem>()
        var onItemClick: ((positionString: String) -> Unit)? = null
        fun setMovies(movies: ArrayList<HousesItem>) {
            this.movieList = movies.toMutableList()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

            val inflater = LayoutInflater.from(parent.context)
            val binding = AdapterMovieBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val colors = arrayOf(
                R.color.design_fab_stroke_top_outer_color,R.color.purple_200,
                R.color.purple_500, R.color.design_default_color_error,R.color.teal_200,R.color.yellow
            )
            val randomColor = colors[Random.nextInt(colors.size)]
            val movie = movieList[position]
            holder.binding.name.text = movie.name
            holder.binding.name.setBackgroundResource(randomColor)

            val id = movieList[position].id
            holder.itemView.setOnClickListener {
                onItemClick?.invoke(id.toString())
            }
        }

        override fun getItemCount(): Int {
            return movieList.size
        }
    }

    class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }
