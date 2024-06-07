package com.dilsahozkan.papafood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Upsert
    suspend fun upsertDataModel(entity: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<FavoriteEntity>)
    @Query("SELECT * FROM Favorite")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: FavoriteEntity)

    @Query("DELETE FROM Favorite")
    suspend fun deleteAll()
}