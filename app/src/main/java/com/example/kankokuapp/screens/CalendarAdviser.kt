package com.example.kankokuapp.screens

import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.kankokuapp.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarAdviser(navController: NavController){
    var selectedItem by remember { mutableStateOf(2) }
    val items = listOf(
        NavBar("Inicio", Icons.Default.Home, AppScreens.AdviserHome.route),
        NavBar("Perfil", Icons.Default.Person, AppScreens.AdviserProfile.route),
        NavBar("Calendario", Icons.Default.CalendarToday, AppScreens.CalendarAdviser.route)
    )
    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.name) },
                        label = { Text(item.name) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(route = item.route)
                        }
                    )
                }
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var date by remember {
                mutableStateOf("")
            }
            AndroidView(factory = { CalendarView(it) }, update = {
                it.setOnDateChangeListener { calendarView, year, month, day ->
                    date = "$day - ${month+1} - $year"
                }
            })
            Text(text = date)
        }
    }
}