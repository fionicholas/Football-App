package com.fionicholas.footballapp.ui.team

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            DetailTeamActivity.start(requireContext(), it)
        }
    }

    private var leagueId : String? = ""

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

        leagueId = (context as DetailLeagueActivity).leagueId
        leagueId?.let { viewModel.loadTeamList(it) }
        setupViewModelTeam()

        tilSearch.setEndIconOnClickListener {
            val query = edtSearch.text.toString()
            if (query != "") {
                viewModel.loadSearchTeam(query)
                setupViewModelSearchTeam()
            }else {
                leagueId?.let { viewModel.loadTeamList(it) }
                setupViewModelTeam()
            }
        }
    }

    private fun setupViewModelTeam() {
        viewModel.teamList.observe(viewLifecycleOwner, loadTeamList)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadTeamList = Observer<List<Team>> {
        Log.v("TAG", "data updated $it")
        teamAdapter.setData(it)
    }

    private fun setupViewModelSearchTeam() {
        viewModel.searchTeam.observe(viewLifecycleOwner, loadSearchTeam)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadSearchTeam = Observer<List<Team>> {
        Log.v("TAG", "data updated $it")
        teamAdapter.setData(it.filter { team-> team.idLeague == leagueId })
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbTeam.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
        Toast.makeText(context, "Data not found!", Toast.LENGTH_SHORT).show()
    }

}