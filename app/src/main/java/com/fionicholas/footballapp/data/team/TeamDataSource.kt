package com.fionicholas.footballapp.data.team

import com.fionicholas.footballapp.data.team.remote.response.Team
import io.reactivex.Observable

interface TeamDataSource {
    fun getTeamList(id : String): Observable<List<Team>>
}