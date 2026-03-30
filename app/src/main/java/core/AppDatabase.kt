package com.example.idress_pm.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.idress_pm.features.wardrobe.data.local.WardrobeDao
import com.example.idress_pm.features.wardrobe.data.local.WardrobeEntity

@Database(entities = [WardrobeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wardrobeDao(): WardrobeDao
}