package com.fionicholas.footballapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.ui.match.MatchAdapter
import kotlinx.android.synthetic.main.fragment_match_favorite.*

class MatchFavoriteFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            MatchFavoriteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val matchAdapter : MatchAdapter by lazy {
        MatchAdapter{
            //to detail
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvMatchFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = matchAdapter
        }

        matchAdapter.setData(getSampleData())
    }

    //TODO : remove data dummy
    private fun getSampleData() : List<Match>{
        return mutableListOf(
            Match(
                "13123",
                "English Premiere League",
                "Man United",
                "Man City",
                "2",
                "3",
                "2020-10-22",
                "14:00",
                "Man United Vs Man City",
                "dsaddsad",
                "Soccer",
                "12313",
                "32131",
                "3",
                "3",
                "4-4-2",
                "4-3-3",
                "",
                "",
                "",
                "1231313",
            )
        )
    }


}