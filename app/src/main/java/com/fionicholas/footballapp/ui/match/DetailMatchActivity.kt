package com.fionicholas.footballapp.ui.match

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseActivity
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import com.fionicholas.footballapp.data.matchfavorite.toFavorite
import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.ui.favorite.match.MatchFavoriteViewModel
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

    private val matchViewModel: MatchViewModel by viewModel()
    private val matchFavoriteViewModel: MatchFavoriteViewModel by viewModel()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var matchData: Match? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        setupToolbar(toolbar, getString(R.string.label_detail_match), true)
        tvToolbarTitle.text = getString(R.string.label_detail_match)

        val matchId = intent.getStringExtra(BundleKeys.MATCH_ID)

        matchId?.let { matchViewModel.loadDetailMatch(it) }
        setupViewModelMatch()

        matchId?.toInt()?.let { matchFavoriteViewModel.loadMatchFavoriteListById(it) }
        setupViewModelFavorite()

    }

    private fun setupViewModelMatch() {
        matchViewModel.detailMatch.observe(this, loadMatchList)
        matchViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        matchViewModel.onMessageError.observe(this, onMessageErrorObserver)
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
            matchData = match
        }
    }

    private fun loadLeagueImage(idLeague: String?) {
        idLeague?.let { matchViewModel.loadDetailLeague(it) }

        matchViewModel.detailLeague.observe(this, loadDetailLeague)
        matchViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        matchViewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadDetailLeague = Observer<List<DetailLeague>> {
        it.forEach { data ->
            Glide.with(this).load(data.banner).into(imgLeague)
        }
    }

    private fun loadBadgeHomeTeam(idHomeTeam: String?) {
        idHomeTeam?.let { matchViewModel.loadBadgeHomeTeam(it) }

        matchViewModel.badgeHome.observe(this, loadBadgeHomeTeam)
        matchViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        matchViewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadBadgeHomeTeam = Observer<List<Team>> {
        it.forEach { data ->
            Glide.with(this).load(data.strTeamBadge).into(imgTeamHome)
        }
    }

    private fun loadBadgeAwayTeam(idAwayTeam: String?) {
        idAwayTeam?.let { matchViewModel.loadBadgeAwayTeam(it) }

        matchViewModel.badgeAway.observe(this, loadBadgeAwayTeam)
        matchViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        matchViewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadBadgeAwayTeam = Observer<List<Team>> {
        it.forEach { data ->
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
                if(isFavorite) matchData?.let { removeFromFavorite(it) } else matchData?.let { addToFavorite(it) }
                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        } else {

            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white)
        }
    }

    private fun setupViewModelFavorite() {
        matchFavoriteViewModel.matchFavoriteById.observe(this, loadFavoriteById)
        matchFavoriteViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        matchFavoriteViewModel.onMessageError.observe(this, onMessageErrorObserver)
        matchFavoriteViewModel.addMatchFavorite.observe(this, {
            Toast.makeText(this, "Added to favorite!", Toast.LENGTH_SHORT).show()
        })

        matchFavoriteViewModel.deleteMatchFavorite.observe(this, {
            Toast.makeText(this, "Removed from favorite!", Toast.LENGTH_SHORT).show()
        })
    }

    private val loadFavoriteById = Observer<List<MatchFavorite>> { favorite ->
        if (favorite.isNotEmpty()) isFavorite = true
    }

    private fun addToFavorite(matchData: Match) {
        matchFavoriteViewModel.addMatchFavorite(matchData.toFavorite())
    }

    private fun removeFromFavorite(matchData: Match) {
        matchData.idEvent?.toInt()?.let { matchFavoriteViewModel.deleteMatchFavorite(it) }
    }
}