package com.example.idress_pm.features.wardrobe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idress_pm.features.wardrobe.data.local.WardrobeDao
import com.example.idress_pm.features.wardrobe.data.local.WardrobeEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class WardrobeViewModel(
    private val dao: WardrobeDao
) : ViewModel() {

    val items = dao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addDummyItem() {
        viewModelScope.launch {
            dao.insert(
                WardrobeEntity(
                    itemId = UUID.randomUUID().toString(),
                    imagePath = "",
                    category = "T-shirt",
                    material = "Coton",
                    thermalIndex = "Light",
                    dominantColor = "Blanc",
                    createdAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteItem(id: String) {
        viewModelScope.launch {
            dao.deleteById(id)
        }
    }
}