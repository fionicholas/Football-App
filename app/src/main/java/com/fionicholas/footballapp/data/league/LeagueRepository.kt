package com.fionicholas.footballapp.data.league

import com.fionicholas.footballapp.data.league.remote.LeagueApiInterface
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import io.reactivex.Observable

class LeagueRepository(private val services: LeagueApiInterface) :
    LeagueDataSource {
    override fun getDetailLeague(id : String): Observable<List<DetailLeague>> {
        return services.getDetailLeague(id).map { it.leagues }
    }
}