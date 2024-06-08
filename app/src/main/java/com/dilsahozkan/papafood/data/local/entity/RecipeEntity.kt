package com.dilsahozkan.papafood.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Recipe")
data class RecipeEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "summary") val summary: String?,
    @ColumnInfo(name = "spoonacularScore") val score: Double?,
    @ColumnInfo(name = "readyInMinutes") val readyInMinutes: Int?,
    @ColumnInfo(name = "pricePerServing") val pricePerServing: Double?,
    @ColumnInfo(name = "saved") val saved: Boolean?,
)