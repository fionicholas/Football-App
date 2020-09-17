package com.fionicholas.footballapp.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseFragment
import com.fionicholas.footballapp.data.league.remote.response.League
import kotlinx.android.synthetic.main.fragment_league.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class LeagueFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            LeagueFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val adapterLeague : LeagueAdapter by lazy {
        LeagueAdapter{
            DetailLeagueActivity.start(context = requireContext(), league = it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupToolbar(toolbar, getString(R.string.label_football_app), false)
        tvToolbarTitle.text = getString(R.string.label_football_app)

        rvLeague.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterLeague
        }

        adapterLeague.setData(getLeagueData())
    }

    private fun getLeagueData(): MutableList<League> {
        val investments = mutableListOf<League>()

        val name = resources.getStringArray(R.array.league_name)
        val id = resources.getStringArray(R.array.league_id)
        val image = resources.obtainTypedArray(R.array.league_image)
        val description = resources.getStringArray(R.array.league_description)

        name.forEachIndexed { index, _ ->
            investments.add(
                League(
                    id[index],
                    name[index],
                    description[index],
                    image.getResourceId(index, 0)
                )
            )
        }

        image.recycle()
        pbLeague.visibility = View.GONE

        return investments
    }
}