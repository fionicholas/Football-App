package com.fionicholas.footballapp.data.league

import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import io.reactivex.Observable

interface LeagueDataSource {
    fun getDetailLeague(id : String): Observable<List<DetailLeague>>
}