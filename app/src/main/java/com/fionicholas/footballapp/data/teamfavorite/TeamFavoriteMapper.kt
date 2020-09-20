package com.fionicholas.footballapp.data.teamfavorite

import com.fionicholas.footballapp.data.team.remote.response.Team
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite

fun Team.toTeamFavorite() : TeamFavorite {
    return TeamFavorite(
        idTeam = idTeam?.toInt() ?: 0 ,
        strTeam = strTeam ?: "",
        strDescriptionEN = strDescriptionEN ?: "",
        strTeamBadge = strTeamBadge ?: "",
        strLeague = strLeague ?: "",
        idLeague = idLeague ?: ""
    )
}

fun TeamFavorite.toTeam() : Team {
    return Team(
        idTeam = idTeam.toString(),
        strTeam = strTeam,
        strDescriptionEN = strDescriptionEN,
        strTeamBadge = strTeamBadge,
        strLeague = strLeague,
        idLeague = idLeague
    )
}