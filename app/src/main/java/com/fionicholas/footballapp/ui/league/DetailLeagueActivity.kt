package com.fionicholas.footballapp.ui.league

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.fionicholas.footballapp.BuildConfig
import com.fionicholas.footballapp.R
import com.fionicholas.footballapp.base.BaseActivity
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.utils.BundleKeys.LEAGUE_ID
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailLeagueActivity : BaseActivity() {

    companion object {
        fun start(context: Context, leagueId: String) {
            context.startActivity(
                Intent(context, DetailLeagueActivity::class.java)
                    .putExtra(LEAGUE_ID, leagueId)
            )
        }
    }

    private val viewModel: LeagueViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        setupCollapsingToolbarDetailLeague()

        val leagueId = intent.getStringExtra(LEAGUE_ID)

        setupToolbar(toolbar, getString(R.string.app_name), false)

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
            val parser = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
            val formatter = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault())
            val date: String = formatter.format(parser.parse(data.firstEvent.toString()))
            tvLeagueName.text = data.name
            tvEvent.text = date
            tvCountry.text = data.country
            tvGender.text = data.gender
            tvDescLeague.text = data.description
            Glide.with(this)
                .load(data.image)
                .into(imgLeague)

            Log.d("ksskskks", data.name.toString())
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
}