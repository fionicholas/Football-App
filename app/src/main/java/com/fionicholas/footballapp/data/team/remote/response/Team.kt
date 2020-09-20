package com.fionicholas.footballapp.data.team.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @SerializedName("idTeam")
    @Expose
    val idTeam: String? = "",

    @SerializedName("strTeam")
    @Expose
    val strTeam: String? = "",

    @SerializedName("intFormedYear")
    @Expose
    val intFormedYear: String? = "",

    @SerializedName("strSport")
    @Expose
    val strSport: String? = "",

    @SerializedName("strDescriptionEN")
    @Expose
    val strDescriptionEN: String? = "",

    @SerializedName("strCountry")
    @Expose
    val strCountry: String? = "",

    @SerializedName("strTeamBadge")
    @Expose
    val strTeamBadge: String? = "",

    @SerializedName("strStadium")
    @Expose
    val strStadium: String? = "",

    @SerializedName("strLeague")
    @Expose
    val strLeague: String? = "",

    @SerializedName("idLeague")
    @Expose
    val idLeague: String? = ""

) : Parcelable