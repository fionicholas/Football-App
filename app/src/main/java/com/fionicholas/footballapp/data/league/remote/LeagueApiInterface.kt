package com.fionicholas.footballapp.data.league.remote

import com.fionicholas.footballapp.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LeagueApiInterface {

    @GET("lookupleague.php")
    fun getDetailLeague(@Query("id") id : String): Observable<BaseResponse>

}