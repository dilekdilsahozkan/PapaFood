package com.dilsahozkan.papafood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilsahozkan.papafood.data.local.dao.FavoriteDao
import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.local.entity.FavoriteEntity
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class, FavoriteEntity::class], version = 2, exportSchema = false)
abstract class RecipeDB : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun favoriteDao(): FavoriteDao
}