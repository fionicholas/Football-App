package com.fionicholas.footballapp.data.matchfavorite

import com.fionicholas.footballapp.data.matchfavorite.local.MatchFavoriteDao
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import io.reactivex.Completable
import io.reactivex.Single

class MatchFavoriteRepository(
    private val matchFavoriteDao: MatchFavoriteDao
) : MatchFavoriteDataSource {

    override fun addMatchFavorite(matchFavorite: MatchFavorite): Completable {
        return Completable.fromAction {
            matchFavoriteDao.save(matchFavorite)
        }
    }

    override fun getMatchFavorite(): Single<List<MatchFavorite>> {
        return matchFavoriteDao.getList()
    }

    override fun getMatchFavoriteById(id: Int): Single<List<MatchFavorite>> {
        return matchFavoriteDao.getItemById(id)
    }

    override fun deleteMatchFavorite(id: Int): Completable {
        return Completable.fromAction {
            matchFavoriteDao.remove(id)
        }
    }

}