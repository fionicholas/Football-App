package com.fionicholas.footballapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.ui.team.TeamAdapter
import kotlinx.android.synthetic.main.fragment_team_favorite.*

class TeamFavoriteFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            TeamFavoriteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val teamAdapter : TeamAdapter by lazy {
        TeamAdapter{
            //to detail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvTeamFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = teamAdapter
        }

        teamAdapter.setData(getSampleData())
    }

    //TODO : remove data dummy
    private fun getSampleData() : List<Team> {
        return mutableListOf(
            Team(
                "21312",
                "Man United",
                "2000",
                "Soccer",
                "LoremLoremLoremLoremLoremLoremLoremLoremLoremLoremLoremLoremLorem",
                "England",
                "https://www.thesportsdb.com/images/media/team/badge/qutsut1424032614.png",
                "Lorem",
                "English Premiere League",
                "21321",
            )
        )
    }


}