package com.fionicholas.footballapp.ui.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.utils.toBaseDateFormat
import kotlinx.android.synthetic.main.item_match.view.*
import java.util.ArrayList

class MatchAdapter(
    val listener: ((data: Match) -> Unit)?
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    private var listMatch = ArrayList<Match>()

    fun setData(data: List<Match>?) {
        if (data == null) return
        this.listMatch.clear()
        this.listMatch.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val matchList = listMatch[position]
        holder.bind(matchList)
    }

    override fun getItemCount(): Int = listMatch.size

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Match) {
            with(itemView) {
                tvLeagueName.text = data.nameLeague
                tvTeamHomeName.text = data.homeTeam
                tvTeamAwayName.text = data.awayTeam
                tvMatchDate.text = data.dateEvent?.toBaseDateFormat()
                tvScoreHome.text = data.homeScore
                tvScoreAway.text = data.awayScore
                itemView.setOnClickListener {
                    listener?.invoke(data)
                }
            }
        }
    }
}