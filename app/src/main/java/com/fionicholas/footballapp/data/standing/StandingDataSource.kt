package com.fionicholas.footballapp.data.standing

import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.data.standing.remote.response.Standing
import io.reactivex.Observable

interface StandingDataSource {
    fun getStanding(id : String): Observable<List<Standing>>
}