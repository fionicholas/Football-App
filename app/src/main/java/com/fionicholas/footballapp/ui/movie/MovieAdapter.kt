package com.fionicholas.footballapp.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.BuildConfig.URL_POSTER
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.movie.remote.response.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*
import java.util.ArrayList

class MovieAdapter(
    val listener: ((movie: MovieItem) -> Unit)?
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieItem>()

    fun setMovies(tvshows: List<MovieItem>?) {
        if (tvshows == null) return
        this.listMovies.clear()
        this.listMovies.addAll(tvshows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieItem) {
            with(itemView) {
                tv_title.text = data.title
                tv_vote.text = data.vote_average.toString()
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }

                Glide.with(itemView.context)
                    .load(URL_POSTER+ data.poster_path)
                    .into(img_poster)
            }
        }
    }
}