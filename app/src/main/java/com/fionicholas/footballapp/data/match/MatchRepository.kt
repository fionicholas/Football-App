package com.fionicholas.footballapp.data.match

import com.fionicholas.footballapp.data.match.remote.MatchApiInterface
import com.fionicholas.footballapp.data.match.remote.response.Match
import io.reactivex.Observable

class MatchRepository(private val services: MatchApiInterface) :
    MatchDataSource {
    override fun getMatchList(id : String): Observable<List<Match>> {
        return services.getMatchList(id).map { it.event }
    }
}