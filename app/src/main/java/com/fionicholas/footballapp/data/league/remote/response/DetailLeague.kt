package com.fionicholas.footballapp.data.league.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailLeague(
    @SerializedName("idLeague")
    @Expose
    val id: String?,

    @SerializedName("strLeague")
    @Expose
    val name: String?,

    @SerializedName("strDescriptionEN")
    @Expose
    val description: String?,

    @SerializedName("dateFirstEvent")
    @Expose
    val firstEvent: String?,

    @SerializedName("strCountry")
    @Expose
    val country: String?,

    @SerializedName("strGender")
    @Expose
    val gender: String?,

    @SerializedName("strBadge")
    @Expose
    val image: String?,

    @SerializedName("strFanart1")
    @Expose
    val banner: String?
)