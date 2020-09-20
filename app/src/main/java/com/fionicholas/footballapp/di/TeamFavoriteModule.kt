package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.teamfavorite.TeamFavoriteDataSource
import com.fionicholas.footballapp.data.teamfavorite.TeamFavoriteRepository
import com.fionicholas.footballapp.ui.favorite.team.TeamFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val teamFavoriteModule = module {

    single<TeamFavoriteDataSource> { TeamFavoriteRepository(get()) }

    viewModel { TeamFavoriteViewModel(get()) }
}