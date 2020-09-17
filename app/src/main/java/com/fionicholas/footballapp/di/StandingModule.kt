package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.standing.StandingDataSource
import com.fionicholas.footballapp.data.standing.StandingRepository
import com.fionicholas.footballapp.data.standing.remote.StandingApiInterface
import com.fionicholas.footballapp.ui.standing.StandingViewModel
import com.fionicholas.footballapp.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val standingModule = module {

    single<StandingApiInterface> { Network.builder().create() }

    single<StandingDataSource> { StandingRepository(get()) }

    viewModel { StandingViewModel(get()) }
}