package com.fionicholas.footballapp.data.teamfavorite.local.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "team_favorite")
data class TeamFavorite(
    @PrimaryKey(autoGenerate = true)
    val idTeam: Int,

    @ColumnInfo(name = "strTeam")
    val strTeam: String,

    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN: String,

    @ColumnInfo(name = "strTeamBadge")
    val strTeamBadge: String,

    @ColumnInfo(name = "strLeague")
    val strLeague: String,

    @ColumnInfo(name = "idLeague")
    val idLeague: String
) : Parcelable