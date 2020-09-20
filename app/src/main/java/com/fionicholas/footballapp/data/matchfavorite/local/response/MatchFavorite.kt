package com.fionicholas.footballapp.data.matchfavorite.local.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "match_favorite")
data class MatchFavorite(
    @PrimaryKey(autoGenerate = true)
    val idEvent: Int,

    @ColumnInfo(name = "nameLeague")
    val nameLeague: String,

    @ColumnInfo(name = "homeTeam")
    val homeTeam: String,

    @ColumnInfo(name = "awayTeam")
    val awayTeam: String,

    @ColumnInfo(name = "homeScore")
    val homeScore: String,

    @ColumnInfo(name = "awayScore")
    val awayScore: String,

    @ColumnInfo(name = "dateEvent")
    val dateEvent: String,

    @ColumnInfo(name = "idLeague")
    val idLeague: String
) : Parcelable