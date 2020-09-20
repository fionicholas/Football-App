package com.fionicholas.footballapp.ui.team

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
import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite
import com.fionicholas.footballapp.data.teamfavorite.toTeamFavorite
import com.fionicholas.footballapp.ui.favorite.team.TeamFavoriteViewModel
import com.fionicholas.footballapp.utils.BundleKeys
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.koin.android.ext.android.inject

class DetailTeamActivity : BaseActivity() {

    companion object {
        fun start(context: Context, team: Team) {
            context.startActivity(
                Intent(context, DetailTeamActivity::class.java)
                    .putExtra(BundleKeys.TEAM_DATA, team)
            )
        }
    }

    private val teamViewModel: TeamViewModel by inject()

    private val teamFavoriteViewModel: TeamFavoriteViewModel by inject()

    private var menuItem: Menu? = null

    private var isFavorite: Boolean = false

    private var teamData: Team? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setupCollapsingToolbarDetailLeague()

        val team = intent.getParcelableExtra<Team>(BundleKeys.TEAM_DATA)

        team?.strTeam?.let { setupToolbar(toolbar, it, true) }

        team?.idTeam?.let { teamViewModel.loadTeamDetail(it) }
        setupViewModelTeam()

        team?.idTeam?.toInt()?.let { teamFavoriteViewModel.loadTeamFavoriteListById(it) }
        setupViewModelFavorite()
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

    private fun setupViewModelTeam() {
        teamViewModel.teamDetail.observe(this, loadTeamDetail)
        teamViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        teamViewModel.onMessageError.observe(this, onMessageErrorObserver)
    }

    private val loadTeamDetail = Observer<List<Team>> {
        it.forEach { team ->
            tvTeamName.text = team.strTeam
            tvLeagueName.text = team.strLeague
            tvValueCountry.text = team.strCountry
            tvValueYear.text = team.intFormedYear
            tvValueSport.text = team.strSport
            tvValueDescription.text = team.strDescriptionEN
            tvValueStadium.text = team.strStadium

            Glide.with(this).load(team.strTeamBadge).into(imgTeamLogo)

            teamData = team
        }
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v("TAG", "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        pbDetailTeam.visibility = visibility
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
                if (isFavorite) teamData?.let { removeFromFavorite(it) } else teamData?.let {
                    addToFavorite(
                        it
                    )
                }
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

    private fun setupViewModelFavorite() {
        teamFavoriteViewModel.teamFavoriteById.observe(this, loadFavoriteById)
        teamFavoriteViewModel.isViewLoading.observe(this, isViewLoadingObserver)
        teamFavoriteViewModel.onMessageError.observe(this, onMessageErrorObserver)
        teamFavoriteViewModel.addTeamFavorite.observe(this, {
            Toast.makeText(this, "Added to favorite!", Toast.LENGTH_SHORT).show()
        })

        teamFavoriteViewModel.deleteTeamFavorite.observe(this, {
            Toast.makeText(this, "Removed from favorite!", Toast.LENGTH_SHORT).show()
        })
    }

    private val loadFavoriteById = Observer<List<TeamFavorite>> { favorite ->
        if (favorite.isNotEmpty()) isFavorite = true
    }

    private fun addToFavorite(teamData: Team) {
        teamFavoriteViewModel.addTeamFavorite(teamData.toTeamFavorite())
    }

    private fun removeFromFavorite(teamData: Team) {
        teamData.idTeam?.toInt()?.let { teamFavoriteViewModel.deleteTeamFavorite(it) }
    }
}