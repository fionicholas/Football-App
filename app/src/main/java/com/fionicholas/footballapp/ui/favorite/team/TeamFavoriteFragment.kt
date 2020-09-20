package com.fionicholas.footballapp.ui.favorite.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite
import com.fionicholas.footballapp.data.teamfavorite.toTeam
import com.fionicholas.footballapp.ui.team.DetailTeamActivity
import com.fionicholas.footballapp.ui.team.TeamAdapter
import kotlinx.android.synthetic.main.fragment_team_favorite.*
import org.koin.android.ext.android.inject

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
            DetailTeamActivity.start(requireContext(), it)
        }
    }

    private val teamFavoriteViewModel : TeamFavoriteViewModel by inject()

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

        setupViewModelTeamFavorite()
    }

    override fun onResume() {
        super.onResume()
        teamFavoriteViewModel.loadTeamFavoriteList()
    }

    private fun setupViewModelTeamFavorite() {
        teamFavoriteViewModel.teamFavoriteList.observe(viewLifecycleOwner, loadTeamFavorite)
        teamFavoriteViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        teamFavoriteViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadTeamFavorite = Observer<List<TeamFavorite>> { listTeamFavorite->
        teamAdapter.setData(listTeamFavorite.map { it.toTeam() })
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbTeamFavorite.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }


}