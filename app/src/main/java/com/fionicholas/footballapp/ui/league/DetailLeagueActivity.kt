package com.fionicholas.footballapp.ui.league

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseActivity
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.data.league.remote.response.League
import com.fionicholas.footballapp.ui.general.adapter.GeneralPagerAdapter
import com.fionicholas.footballapp.ui.match.MatchFragment
import com.fionicholas.footballapp.ui.standing.StandingFragment
import com.fionicholas.footballapp.ui.team.TeamFragment
import com.fionicholas.footballapp.utils.BundleKeys.LEAGUE_DATA
import com.fionicholas.footballapp.utils.toBaseDateFormat
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailLeagueActivity : BaseActivity() {

    companion object {
        fun start(context: Context, league: League) {
            context.startActivity(
                Intent(context, DetailLeagueActivity::class.java)
                    .putExtra(LEAGUE_DATA, league)
            )
        }
    }

    private val viewModel: LeagueViewModel by viewModel()

    var leagueId : String? = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        setupCollapsingToolbarDetailLeague()

        val dataLeague = intent.getParcelableExtra<League>(LEAGUE_DATA)

        leagueId = dataLeague?.id

        dataLeague?.name?.let { setupToolbar(toolbar, it, true) }
        setupViewPagerDetailLeague()

        leagueId?.let { viewModel.loadDetailLeague(it) }
        setupViewModelDetailLeague()

    }

    private fun setupCollapsingToolbarDetailLeague() {
        setSupportActionBar(toolbar)

        toolbarLayout.setCollapsedTitleTextColor(
            ContextCompat.getColor(this, R.color.colorWhite)
        )
        toolbarLayout.setExpandedTitleColor(
            ContextCompat.getColor(this, R.color.colorTransparent)
        )
    }

    private fun setupViewModelDetailLeague() {
        viewModel.detailLeagues.observe(this, loadDetailLeagues)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }


    private val loadDetailLeagues = Observer<List<DetailLeague>> {
        Log.v("TAG", "data updated $it")
        it.forEach { data ->
            tvLeagueName.text = data.name
            tvEvent.text = data.firstEvent?.toBaseDateFormat()
            tvCountry.text = data.country
            tvGender.text = data.gender
            tvDescLeague.text = data.description
            Glide.with(this)
                .load(data.image)
                .into(imgLeague)
        }
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbDetailLeague.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }

    private fun setupViewPagerDetailLeague() {
        val pages = listOf(
            MatchFragment.newInstance(),
            StandingFragment.newInstance(),
            TeamFragment.newInstance()
        )

        val pageTitles = listOf(
            getString(R.string.title_tab_match),
            getString(R.string.title_tab_standing),
            getString(R.string.title_tab_team)
        )

        vpDetailLeague.apply {
            adapter = GeneralPagerAdapter(
                supportFragmentManager,
                pages = pages,
                title = pageTitles
            )
            offscreenPageLimit = 3
        }

        tabDetailLeague.setupWithViewPager(vpDetailLeague)
    }

}