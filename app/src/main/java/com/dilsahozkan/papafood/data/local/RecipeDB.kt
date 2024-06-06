package com.dilsahozkan.papafood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dilsahozkan.papafood.data.local.dao.RecipeDao
import com.dilsahozkan.papafood.data.local.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
abstract class RecipeDB : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}