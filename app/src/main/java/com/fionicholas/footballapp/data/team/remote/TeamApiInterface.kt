package com.fionicholas.footballapp.data.team.remote

import com.fionicholas.footballapp.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApiInterface {
    @GET("lookup_all_teams.php")
    fun getTeamList(@Query("id") id : String): Observable<BaseResponse>
}