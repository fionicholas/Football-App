package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.team.TeamDataSource
import com.fionicholas.footballapp.data.team.TeamRepository
import com.fionicholas.footballapp.data.team.remote.TeamApiInterface
import com.fionicholas.footballapp.ui.team.TeamViewModel
import com.fionicholas.footballapp.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val teamModule = module {

    single<TeamApiInterface> { Network.builder().create() }

    single<TeamDataSource> { TeamRepository(get()) }

    viewModel { TeamViewModel(get()) }
}