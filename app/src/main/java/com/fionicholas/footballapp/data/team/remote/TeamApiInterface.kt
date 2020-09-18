package com.fionicholas.footballapp.data.team.remote

import com.fionicholas.footballapp.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApiInterface {
    @GET("lookup_all_teams.php")
    fun getTeamList(@Query("id") id : String): Observable<BaseResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String): Observable<BaseResponse>

    @GET("searchteams.php")
    fun getSearchTeam(@Query("t") id: String): Observable<BaseResponse>
}