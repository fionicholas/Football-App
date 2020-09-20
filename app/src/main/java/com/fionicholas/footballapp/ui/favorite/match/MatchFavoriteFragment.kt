package com.fionicholas.footballapp.ui.favorite.match

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import com.fionicholas.footballapp.data.matchfavorite.toMatch
import com.fionicholas.footballapp.ui.match.DetailMatchActivity
import com.fionicholas.footballapp.ui.match.MatchAdapter
import kotlinx.android.synthetic.main.fragment_match_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class MatchFavoriteFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            MatchFavoriteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private val matchAdapter: MatchAdapter by lazy {
        MatchAdapter {
            it.idEvent?.let { matchId -> DetailMatchActivity.start(requireContext(), matchId) }
        }
    }

    private val matchFavoriteViewModel: MatchFavoriteViewModel by viewModel()

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


        setupViewModelMatchFavorite()
    }

    override fun onResume() {
        super.onResume()
        matchFavoriteViewModel.loadMatchFavoriteList()
    }

    private fun setupViewModelMatchFavorite() {
        matchFavoriteViewModel.matchFavoriteList.observe(viewLifecycleOwner, loadMatchFavorite)
        matchFavoriteViewModel.isViewLoading.observe(viewLifecycleOwner, isViewLoadingObserver)
        matchFavoriteViewModel.onMessageError.observe(viewLifecycleOwner, onMessageErrorObserver)
    }

    private val loadMatchFavorite = Observer<List<MatchFavorite>> {listMatchFavorite->
        matchAdapter.setData(listMatchFavorite.map { it.toMatch() })
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbMatchFavorite.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }


}