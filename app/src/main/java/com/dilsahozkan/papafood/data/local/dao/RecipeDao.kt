package com.dilsahozkan.papafood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<RecipeEntity>)
    @Query("SELECT * FROM Recipe")
    fun getAll(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM Recipe LIMIT :limit")
    fun getAll(limit:Int): List<RecipeEntity>

    @Query("SELECT * FROM Recipe ORDER BY RANDOM() LIMIT :limit")
    fun getRandom(limit:Int): List<RecipeEntity>

    @Query("SELECT count(*) FROM Recipe")
    fun count(): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: RecipeEntity)

    @Query("SELECT * FROM Recipe WHERE id = :id LIMIT 1")
    fun get(id:Int): RecipeEntity

}