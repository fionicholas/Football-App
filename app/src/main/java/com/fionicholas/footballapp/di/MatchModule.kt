package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.match.MatchDataSource
import com.fionicholas.footballapp.data.match.MatchRepository
import com.fionicholas.footballapp.data.match.remote.MatchApiInterface
import com.fionicholas.footballapp.ui.match.MatchViewModel
import com.fionicholas.footballapp.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val matchModule = module {

    single<MatchApiInterface> { Network.builder().create() }

    single<MatchDataSource> { MatchRepository(get()) }

    viewModel { MatchViewModel(get()) }
}