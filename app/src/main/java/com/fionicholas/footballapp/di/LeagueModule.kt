package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.league.LeagueDataSource
import com.fionicholas.footballapp.data.league.LeagueRepository
import com.fionicholas.footballapp.data.league.remote.LeagueApiInterface
import com.fionicholas.footballapp.ui.league.LeagueViewModel
import com.fionicholas.footballapp.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val leagueModule = module {

    single<LeagueApiInterface> { Network.builder().create() }

    single<LeagueDataSource> { LeagueRepository(get()) }

    viewModel { LeagueViewModel(get()) }
}