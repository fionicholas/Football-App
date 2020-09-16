package com.fionicholas.footballapp.ui.league

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.league.remote.response.League
import kotlinx.android.synthetic.main.item_leagues.view.*
import java.util.*

class LeagueAdapter(
    val listener: ((data: League) -> Unit)?
) : RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {
    private var listLeague = ArrayList<League>()

    fun setData(data: List<League>?) {
        if (data == null) return
        this.listLeague.clear()
        this.listLeague.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_leagues, parent, false)
        return LeagueViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val leagues = listLeague[position]
        holder.bind(leagues)
    }

    override fun getItemCount(): Int = listLeague.size

    inner class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: League) {
            with(itemView) {
                tvLeagueName.text = data.name
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }

                Glide.with(itemView.context)
                    .load(data.image)
                    .into(imgLeagues)
            }
        }
    }
}