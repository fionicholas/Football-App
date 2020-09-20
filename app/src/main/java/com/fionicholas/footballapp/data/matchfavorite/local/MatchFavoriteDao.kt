package com.fionicholas.footballapp.data.matchfavorite.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fionicholas.footballapp.data.matchfavorite.local.response.MatchFavorite
import io.reactivex.Single

@Dao
abstract class MatchFavoriteDao {
    @Query("SELECT * FROM match_favorite WHERE idEvent = :intId")
    abstract fun getItemById(intId: Int?): Single<List<MatchFavorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun save(favorite: MatchFavorite)

    @Query("SELECT * FROM match_favorite")
    abstract fun getList(): Single<List<MatchFavorite>>

    @Query("DELETE FROM match_favorite WHERE idEvent = :intId")
    abstract fun remove(intId: Int?)

    @Query("DELETE FROM match_favorite")
    abstract fun removeAll()
}