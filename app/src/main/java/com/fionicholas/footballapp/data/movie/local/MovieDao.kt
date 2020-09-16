package com.fionicholas.footballapp.data.movie.local

import androidx.room.Dao
import androidx.room.Query
import com.fionicholas.footballapp.data.movie.local.response.MovieFavorite
import io.reactivex.Single

@Dao
abstract class MovieDao {
    @Query("SELECT * FROM movie WHERE id = :intId")
    abstract fun getItemById(intId: Int?): Single<MovieFavorite>

    @Query("SELECT * FROM movie")
    abstract fun getList(): Single<List<MovieFavorite>>

    @Query("DELETE FROM movie WHERE id = :intId")
    abstract fun remove(intId: Int?)

    @Query("DELETE FROM movie")
    abstract fun removeAll()
}