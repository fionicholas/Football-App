package com.fionicholas.footballapp.data.match

import com.fionicholas.footballapp.data.match.remote.response.Match
import io.reactivex.Observable

interface MatchDataSource {
    fun getMatchList(id : String): Observable<List<Match>>
}