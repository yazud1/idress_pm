package com.example.idress_pm.features.wardrobe.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wardrobe")
data class WardrobeEntity(
    @PrimaryKey val itemId: String,
    val imagePath: String,
    val category: String,
    val material: String?,
    val thermalIndex: String?,
    val dominantColor: String?,
    val createdAt: Long
)