package com.example.idress_pm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IddressApp()
        }
    }
}

enum class BottomTab(val label: String) {
    Home("Home"),
    Wardrobe("Wardrobe"),
    Events("Events"),
    Account("Account")
}

@Composable
fun IddressApp() {
    var selectedTab by remember { mutableStateOf(BottomTab.Wardrobe) }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selectedTab = selectedTab,
                    onTabSelected = { selectedTab = it }
                )
            },
            containerColor = Color(0xFFF7F7F7)
        ) { padding ->
            when (selectedTab) {
                BottomTab.Home -> HomeScreen(Modifier.padding(padding))
                BottomTab.Wardrobe -> WardrobeScreen(Modifier.padding(padding))
                BottomTab.Events -> PlaceholderScreen("Events", Modifier.padding(padding))
                BottomTab.Account -> PlaceholderScreen("Account", Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun BottomNavBar(
    selectedTab: BottomTab,
    onTabSelected: (BottomTab) -> Unit
) {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedTab == BottomTab.Home,
            onClick = { onTabSelected(BottomTab.Home) },
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.Wardrobe,
            onClick = { onTabSelected(BottomTab.Wardrobe) },
            icon = { Icon(Icons.Outlined.Checkroom, contentDescription = "Wardrobe") },
            label = { Text("Wardrobe") }
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.Events,
            onClick = { onTabSelected(BottomTab.Events) },
            icon = { Icon(Icons.Outlined.CalendarMonth, contentDescription = "Events") },
            label = { Text("Events") }
        )
        NavigationBarItem(
            selected = selectedTab == BottomTab.Account,
            onClick = { onTabSelected(BottomTab.Account) },
            icon = { Icon(Icons.Outlined.AccountCircle, contentDescription = "Account") },
            label = { Text("Account") }
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        TopHeader(title = "iDress")
        Spacer(modifier = Modifier.height(16.dp))

        WeatherBar()
        Spacer(modifier = Modifier.height(16.dp))

        OutfitCard(
            title = "Look 1: Smart Casual",
            pieces = listOf(
                "Cashmere crewneck",
                "Wool blend trousers",
                "Leather sneakers"
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutfitCard(
            title = "Look 2: Urban Casual Chic",
            pieces = listOf(
                "Wool blend scarf",
                "Tailored trousers",
                "Loafer sneakers"
            )
        )

        Spacer(modifier = Modifier.height(18.dp))

        ScanButton()
    }
}

@Composable
fun WardrobeScreen(modifier: Modifier = Modifier) {
    val filters = listOf("All", "Outerwear", "Tops", "Bottoms")
    var selectedFilter by remember { mutableStateOf("All") }

    val items = listOf(
        "Coat", "Blue T-shirt", "Jeans", "Shoes"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopHeader(title = "My Wardrobe")
        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            filters.forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {
            items(items) { item ->
                WardrobeItemCard(item)
            }

            item {
                ScanGridCard()
            }
        }
    }
}

@Composable
fun TopHeader(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WeatherBar() {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Paris, 17°C ☁️", fontWeight = FontWeight.Medium)
            Text("Spring look", color = Color.Gray)
        }
    }
}

@Composable
fun OutfitCard(
    title: String,
    pieces: List<String>
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(190.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFEDEDED)),
                contentAlignment = Alignment.Center
            ) {
                Text("Outfit", color = Color.Gray)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )

                pieces.forEach {
                    Text(
                        text = "• $it",
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun WardrobeItemCard(label: String) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFEDEDED)),
                contentAlignment = Alignment.Center
            ) {
                Text(label, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = label,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ScanGridCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.85f)
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFD8F6FF))
            .border(1.dp, Color(0xFF9EDDEA), RoundedCornerShape(18.dp))
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Scan",
                tint = Color(0xFF2BA8C8),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "SCAN",
                color = Color(0xFF2BA8C8),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ScanButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF8DE6F8),
            contentColor = Color.Black
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Outlined.PhotoCamera,
            contentDescription = "Scan"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("SCAN", fontWeight = FontWeight.Bold)
    }
}

@Composable
fun PlaceholderScreen(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$name screen")
    }
}