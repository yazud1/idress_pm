package com.example.idress_pm.features.wardrobe.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WardrobeDao {
    @Query("SELECT * FROM wardrobe ORDER BY createdAt DESC")
    fun getAllItems(): Flow<List<WardrobeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: WardrobeEntity)

    @Query("DELETE FROM wardrobe WHERE itemId = :id")
    suspend fun deleteById(id: String)
}