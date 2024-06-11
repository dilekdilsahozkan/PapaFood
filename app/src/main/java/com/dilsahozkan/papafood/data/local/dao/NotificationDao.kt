package com.dilsahozkan.papafood.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dilsahozkan.papafood.data.local.entity.NotificationEntity

@Dao
interface NotificationDao {
    @Query("SELECT * FROM NOTIFICATION ORDER BY id DESC LIMIT 20")
    fun getNotifications(): List<NotificationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notifications: List<NotificationEntity>)

    @Query("DELETE FROM NOTIFICATION")
    suspend fun deleteAll()
}