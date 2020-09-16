package com.fionicholas.footballapp.data.movie

import com.fionicholas.footballapp.data.movie.remote.MovieApiInterface
import com.fionicholas.footballapp.data.movie.remote.response.MovieResponse
import io.reactivex.Observable

class MovieRepository(private val services: MovieApiInterface) :
    MovieDataSource {

    override fun getPopularMovie(): Observable<MovieResponse> {
        return services.getPopularMovie()
    }

}