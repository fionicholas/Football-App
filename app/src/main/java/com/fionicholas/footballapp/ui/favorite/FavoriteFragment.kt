package com.fionicholas.footballapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseFragment
import com.fionicholas.footballapp.ui.favorite.match.MatchFavoriteFragment
import com.fionicholas.footballapp.ui.favorite.team.TeamFavoriteFragment
import com.fionicholas.footballapp.ui.general.adapter.GeneralPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.layout_toolbar_main.*

class FavoriteFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupToolbar(toolbar, getString(R.string.label_favorite), false)
        tvToolbarTitle.text = getString(R.string.label_favorite)
        setupViewPagerFavorite()
    }

    private fun setupViewPagerFavorite() {
        val pages = listOf(
            MatchFavoriteFragment.newInstance(),
            TeamFavoriteFragment.newInstance()
        )

        val pageTitles = listOf(
            getString(R.string.title_tab_match),
            getString(R.string.title_tab_team)
        )

        vpFavorite.apply {
            adapter = GeneralPagerAdapter(
                childFragmentManager,
                pages = pages,
                title = pageTitles
            )
            offscreenPageLimit = 2
        }

        tabFavorite.setupWithViewPager(vpFavorite)
    }

}