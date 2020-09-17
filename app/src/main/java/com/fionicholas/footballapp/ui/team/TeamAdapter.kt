package com.fionicholas.footballapp.ui.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.team.remote.response.Team
import kotlinx.android.synthetic.main.item_team.view.*
import java.util.*

class TeamAdapter(
    val listener: ((data: Team) -> Unit)?
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    private var listMatch = ArrayList<Team>()

    fun setData(data: List<Team>?) {
        if (data == null) return
        this.listMatch.clear()
        this.listMatch.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val matchList = listMatch[position]
        holder.bind(matchList)
    }

    override fun getItemCount(): Int = listMatch.size

    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Team) {
            with(itemView) {
                tvTeamName.text = data.strTeam
                tvTeamDesc.text = data.strDescriptionEN
                Glide.with(context)
                    .load(data.strTeamBadge)
                    .into(imgTeam)
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }
            }
        }
    }
}