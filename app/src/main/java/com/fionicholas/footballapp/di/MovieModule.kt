package com.fionicholas.footballapp.di

import com.fionicholas.footballapp.data.movie.MovieDataSource
import com.fionicholas.footballapp.data.movie.MovieRepository
import com.fionicholas.footballapp.data.movie.remote.MovieApiInterface
import com.fionicholas.footballapp.ui.movie.MovieViewModel
import com.fionicholas.footballapp.utils.Network
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.create

val movieModule = module {

    single<MovieApiInterface> { Network.builder().create() }

    single<MovieDataSource> { MovieRepository(get()) }

    viewModel { MovieViewModel(get()) }
}