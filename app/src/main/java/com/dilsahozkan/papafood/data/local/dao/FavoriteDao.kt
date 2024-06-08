package com.dilsahozkan.papafood.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<FavoriteEntity>)

    @Query("SELECT * FROM Favorite")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(recipe: FavoriteEntity)
}