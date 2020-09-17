package com.fionicholas.footballapp.data.standing.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Standing(
    @SerializedName("teamid")
    @Expose
    val teamId: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("played")
    @Expose
    val played: String?,

    @SerializedName("goalsfor")
    @Expose
    val goalsfor: String?,

    @SerializedName("goalsagainst")
    @Expose
    val goalsagainst: String?,

    @SerializedName("win")
    @Expose
    val win: String?,

    @SerializedName("loss")
    @Expose
    val loss: String?,

    @SerializedName("draw")
    @Expose
    val draw: String?
)