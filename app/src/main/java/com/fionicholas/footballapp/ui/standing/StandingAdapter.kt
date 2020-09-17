package com.fionicholas.footballapp.ui.standing

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.standing.remote.response.Standing
import kotlinx.android.synthetic.main.item_standing.view.*
import java.util.*

class StandingAdapter(
    val listener: ((data: Standing) -> Unit)?
) : RecyclerView.Adapter<StandingAdapter.StandingViewHolder>() {
    private var listStanding = ArrayList<Standing>()

    fun setData(data: List<Standing>?) {
        if (data == null) return
        this.listStanding.clear()
        this.listStanding.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        return StandingViewHolder(view)
    }

    override fun onBindViewHolder(holder: StandingViewHolder, position: Int) {
        val matchList = listStanding[position]
        holder.bind(matchList)
    }

    override fun getItemCount(): Int = listStanding.size

    inner class StandingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Standing) {
            with(itemView) {
                tvTeamName.text = data.name
                tvPlayed.text = data.played
                tvWin.text = data.win
                tvLose.text = data.loss
                tvDraw.text = data.draw
                tvGM.text = data.goalsagainst
                tvGK.text = data.goalsfor
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }
            }
        }
    }
}