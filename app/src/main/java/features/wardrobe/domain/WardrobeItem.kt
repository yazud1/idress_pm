package com.example.idress.features.wardrobe.domain

data class WardrobeItem(
    val itemId: String,
    val imagePath: String,
    val category: String,
    val material: String?,
    val thermalIndex: String?,
    val dominantColor: String?,
    val createdAt: Long
)