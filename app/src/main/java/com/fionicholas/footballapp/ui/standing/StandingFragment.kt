package com.fionicholas.footballapp.ui.standing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.standing.remote.response.Standing
import com.fionicholas.footballapp.ui.league.DetailLeagueActivity
import kotlinx.android.synthetic.main.fragment_standing.*
import org.koin.android.ext.android.inject

class StandingFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            StandingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val standingAdapter : StandingAdapter by lazy {
        StandingAdapter{
            //to detail
        }
    }

    private val viewModel : StandingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvStanding.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = standingAdapter
        }

        val leagueId = (context as DetailLeagueActivity).leagueId
        leagueId?.let { viewModel.loadStanding(it) }
        setupViewModelStanding()
    }

    private fun setupViewModelStanding() {
        viewModel.standing.observe(viewLifecycleOwner, loadMatchList)
        viewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        viewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadMatchList = Observer<List<Standing>> {
        Log.v("TAG", "data updated $it")
        standingAdapter.setData(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbStanding.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }
}