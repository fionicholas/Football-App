package com.fionicholas.footballapp.data.match.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match (
    @SerializedName("idEvent")
    @Expose
    val idEvent: String? = "",

    @SerializedName("strLeague")
    @Expose
    val nameLeague: String? = "",

    @SerializedName("strHomeTeam")
    @Expose
    val homeTeam: String? = "",

    @SerializedName("strAwayTeam")
    @Expose
    val awayTeam: String? = "",

    @SerializedName("intHomeScore")
    @Expose
    val homeScore: String? = "",

    @SerializedName("intAwayScore")
    @Expose
    val awayScore: String? = "",

    @SerializedName("dateEvent")
    @Expose
    val dateEvent: String? = "",

    @SerializedName("strTime")
    @Expose
    val time: String? = "",

    @SerializedName("strEvent")
    @Expose
    val eventName: String? = "",

    @SerializedName("strTeamBadge")
    @Expose
    val teamBadge: String? = "",

    @SerializedName("strSport")
    @Expose
    val sport: String? = "",

    @SerializedName("idTeam")
    @Expose
    val idTeam: String? = "",

    @SerializedName("idHomeTeam")
    @Expose
    val idHomeTeam: String? = "",

    @SerializedName("intHomeShots")
    @Expose
    val homeShoot: String? = "",

    @SerializedName("intAwayShots")
    @Expose
    val awayShoot: String? = "",

    @SerializedName("strHomeFormation")
    @Expose
    val homeFormation: String? = "",

    @SerializedName("strAwayFormation")
    @Expose
    val awayFormation: String? = "",

    @SerializedName("strHomeGoalDetails")
    @Expose
    val homeGoalDetail: String? = "",

    @SerializedName("strAwayGoalDetails")
    @Expose
    val awayGoalDetail: String? = "",

    @SerializedName("idAwayTeam")
    @Expose
    val idAwayTeam: String? = "",

    @SerializedName("idLeague")
    @Expose
    val idLeague: String? = ""
) : Parcelable