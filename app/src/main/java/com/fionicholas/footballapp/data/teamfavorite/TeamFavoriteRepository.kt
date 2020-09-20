package com.fionicholas.footballapp.data.teamfavorite

import com.fionicholas.footballapp.data.FavoriteDao
import com.fionicholas.footballapp.data.teamfavorite.local.response.TeamFavorite
import io.reactivex.Completable
import io.reactivex.Single

class TeamFavoriteRepository(
    private val favoriteDao: FavoriteDao
) : TeamFavoriteDataSource {
    override fun addTeamFavorite(teamFavorite: TeamFavorite): Completable {
        return Completable.fromAction {
            favoriteDao.addFavoriteTeam(teamFavorite)
        }
    }

    override fun getTeamFavorite(): Single<List<TeamFavorite>> {
        return favoriteDao.getListFavoriteTeam()
    }

    override fun getTeamFavoriteById(id: Int): Single<List<TeamFavorite>> {
        return favoriteDao.getFavoriteTeamById(id)
    }

    override fun deleteTeamFavorite(id: Int): Completable {
        return Completable.fromAction {
            favoriteDao.deleteFavoriteTeam(id)
        }
    }


}