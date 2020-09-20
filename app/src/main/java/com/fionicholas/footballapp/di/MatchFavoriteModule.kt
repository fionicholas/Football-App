package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.AppDatabase
import com.fionicholas.footballapp.data.matchfavorite.MatchFavoriteDataSource
import com.fionicholas.footballapp.data.matchfavorite.MatchFavoriteRepository
import com.fionicholas.footballapp.ui.favorite.match.MatchFavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val matchFavoriteModule = module {

    single {
        val appDatabase: AppDatabase = get()
        return@single appDatabase.favoriteDao()
    }

    single<MatchFavoriteDataSource> { MatchFavoriteRepository(get()) }

    viewModel { MatchFavoriteViewModel(get()) }
}