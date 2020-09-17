package com.fionicholas.footballapp.data.match.remote

import com.fionicholas.footballapp.base.BaseResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MatchApiInterface {
    @GET("eventspastleague.php")
    fun getMatchList(@Query("id") id : String): Observable<BaseResponse>
}