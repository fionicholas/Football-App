package com.fionicholas.footballapp.data.match

import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.data.team.remote.response.Team
import io.reactivex.Observable

interface MatchDataSource {
    fun getMatchList(id : String): Observable<List<Match>>
    fun getDetailMatch(id : String): Observable<List<Match>>
    fun getDetailLeague(id : String): Observable<List<DetailLeague>>
    fun getTeamDetail(id : String): Observable<List<Team>>
}