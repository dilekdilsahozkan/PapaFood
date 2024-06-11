package com.dilsahozkan.papafood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: List<RecipeEntity>)

    @Query("DELETE FROM RECIPE")
    suspend fun deleteAll()

    @Query("SELECT * FROM RECIPE")
    suspend fun getAllRecipes(): List<RecipeEntity>

}