package com.fionicholas.footballapp.ui.match

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseActivity
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.utils.BundleKeys
import com.fionicholas.footballapp.utils.toBaseDateFormat
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMatchActivity : BaseActivity() {

    companion object {
        fun start(context: Context, matchId: String) {
            context.startActivity(
                Intent(context, DetailMatchActivity::class.java)
                    .putExtra(BundleKeys.MATCH_ID, matchId)
            )
        }
    }

    private val viewModel: MatchViewModel by viewModel()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setupToolbar(toolbar, getString(R.string.label_detail_match), true)
        tvToolbarTitle.text = getString(R.string.label_detail_match)

        val matchId = intent.getStringExtra(BundleKeys.MATCH_ID)

        matchId?.let { viewModel.loadDetailMatch(it) }
        setupViewModelMatch()
    }

    private fun setupViewModelMatch() {
        viewModel.detailMatch.observe(this, loadMatchList)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }


    private val loadMatchList = Observer<List<Match>> {
        it.forEach { match ->
            tvTeamHomeName.text = match.homeTeam
            tvTeamAwayName.text = match.awayTeam
            tvMatchDate.text = match.dateEvent?.toBaseDateFormat()
            tvScoreHome.text = match.homeScore
            tvScoreAway.text = match.awayScore
            tvMatchName.text = match.eventName
            tvScoreHomeDetail.text = match.homeScore
            tvScoreAwayDetail.text = match.awayScore
            tvShootsHome.text = match.homeShoot
            tvShootsAway.text = match.awayShoot
            tvFormationHome.text = match.homeFormation
            tvFormationAway.text = match.awayFormation
            tvValueGoalHome.text = match.homeGoalDetail
            tvValueGoalAway.text = match.awayGoalDetail
            loadLeagueImage(match.idLeague)
            loadBadgeHomeTeam(match.idHomeTeam)
            loadBadgeAwayTeam(match.idAwayTeam)
        }
    }

    private fun loadLeagueImage(idLeague: String?) {
        idLeague?.let { viewModel.loadDetailLeague(it) }

        viewModel.detailLeague.observe(this, loadDetailLeague)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadDetailLeague = Observer<List<DetailLeague>> {
        it.forEach {data ->
            Glide.with(this).load(data.banner).into(imgLeague)
        }
    }

    private fun loadBadgeHomeTeam(idHomeTeam: String?) {
        idHomeTeam?.let { viewModel.loadBadgeHomeTeam(it) }

        viewModel.badgeHome.observe(this, loadBadgeHomeTeam)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }
    private val loadBadgeHomeTeam = Observer<List<Team>> {
        it.forEach {data ->
            Glide.with(this).load(data.strTeamBadge).into(imgTeamHome)
        }
    }

    private fun loadBadgeAwayTeam(idAwayTeam: String?) {
        idAwayTeam?.let { viewModel.loadBadgeAwayTeam(it) }

        viewModel.badgeAway.observe(this, loadBadgeAwayTeam)
        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadBadgeAwayTeam = Observer<List<Team>> {
        it.forEach {data ->
            Glide.with(this).load(data.strTeamBadge).into(imgTeamAway)
        }
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbDetailMatch.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<String> {
        Log.e("TAG", "onMessageError $it")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menuFavorite -> {
                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white)
    }
}