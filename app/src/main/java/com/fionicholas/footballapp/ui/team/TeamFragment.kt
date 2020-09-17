package com.fionicholas.footballapp.ui.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.ui.league.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_team.*
import org.koin.android.ext.android.inject

class TeamFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            TeamFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val teamAdapter : TeamAdapter by lazy {
        TeamAdapter{
            //to detail
        }
    }

    private val viewModel : TeamViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvTeam.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = teamAdapter
        }

        val leagueId = (context as DetailLeagueActivity).leagueId
        leagueId?.let { viewModel.loadTeamList(it) }
        setupViewModelMatch()
    }

    private fun setupViewModelMatch() {
        viewModel.teamList.observe(viewLifecycleOwner, loadTeamList)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadTeamList = Observer<List<Team>> {
        Log.v("TAG", "data updated $it")
        teamAdapter.setData(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbTeam.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }

}