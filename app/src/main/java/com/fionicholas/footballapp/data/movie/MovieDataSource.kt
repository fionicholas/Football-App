package com.fionicholas.footballapp.data.movie

import com.fionicholas.footballapp.data.movie.remote.response.MovieResponse
import io.reactivex.Observable

interface MovieDataSource {
    fun getPopularMovie(): Observable<MovieResponse>
}