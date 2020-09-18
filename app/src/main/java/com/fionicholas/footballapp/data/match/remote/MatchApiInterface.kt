package com.fionicholas.footballapp.data.match.remote

import com.fionicholas.footballapp.base.BaseResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApiInterface {
    @GET("eventspastleague.php")
    fun getMatchList(@Query("id") id : String): Observable<BaseResponse>

    @GET("lookupevent.php")
    fun getDetailMatch(@Query("id") id : String): Observable<BaseResponse>

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id : String): Observable<BaseResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String): Observable<BaseResponse>

    @GET("searchevents.php")
    fun getSearchMatch(@Query("e") query: String): Observable<BaseResponse>
}