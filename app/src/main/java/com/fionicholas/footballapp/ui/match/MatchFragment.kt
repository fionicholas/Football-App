package com.fionicholas.footballapp.ui.match

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
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.ui.league.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.koin.android.viewmodel.ext.android.viewModel

class MatchFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            MatchFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val matchAdapter : MatchAdapter by lazy {
        MatchAdapter{
            it.idEvent?.let { matchId -> DetailMatchActivity.start(requireContext(), matchId) }
        }
    }

    private val viewModel: MatchViewModel by viewModel()

    private var leagueId : String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvMatch.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = matchAdapter
            isNestedScrollingEnabled = false
        }

        leagueId = (context as DetailLeagueActivity).leagueId
        leagueId?.let { viewModel.loadMatchList(it) }
        setupViewModelMatch()

        tilSearch.setEndIconOnClickListener {
            val query = edtSearch.text.toString()
            if (query != "") {
                viewModel.loadSearchMatch(query)
                setupViewModelSearchMatch()
            }else {
                leagueId?.let { viewModel.loadMatchList(it) }
                setupViewModelMatch()
            }
        }
    }

    private fun setupViewModelMatch() {
        viewModel.matchList.observe(viewLifecycleOwner, loadMatchList)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadMatchList = Observer<List<Match>> {
        Log.v("TAG", "data updated $it")
        matchAdapter.setData(it)
    }

    private fun setupViewModelSearchMatch() {
        viewModel.searchMatch.observe(viewLifecycleOwner, loadSearchMatch)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadSearchMatch = Observer<List<Match>> {
        Log.v("TAG", "data updated $it")
        matchAdapter.setData(it.filter { match -> match.idLeague == leagueId })
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbMatch.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
        Toast.makeText(context, "Data not found!", Toast.LENGTH_SHORT).show()
    }

}