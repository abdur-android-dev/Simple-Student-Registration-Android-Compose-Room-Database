package com.abdur.simplestudentregistrationsystem.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.abdur.simplestudentregistrationsystem.ui.navigation.BottomNavItem
import com.abdur.studentroomdbproject.ui.navigation.Routes
import com.abdur.studentroomdbproject.ui.screens.MainScreen
import com.abdur.studentroomdbproject.ui.screens.ProfileView

@Composable
fun BottomBarScreen(studentID: String, navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.ProfileScreen,
        BottomNavItem.HomeScreen,
        BottomNavItem.AllScreen
    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black
            ) {
                screens.forEachIndexed { index, screen ->

                    NavigationBarItem(
                        selected = selectedItemIndex == index,
                        label = {
                            Text(text = screen.title.toString(), color = Color.White)
                        },
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = screen.title, tint = if(selectedItemIndex==index) Color.Black else Color.White)
                        },
                        onClick = {
                            selectedItemIndex = index
                            when (selectedItemIndex) {
                                0 -> navController.navigate(Routes.PROFILE_SCREEN.replace("{studentID}", studentID))
                                1 -> navController.navigate(Routes.HOME_SCREEN)
                                2 -> navController.navigate(Routes.ALL_RECORD_SCREEN)
                            }
                        }
                    )
                }
            }
        }
    ) {
        // Content of BottomBarScreen
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (selectedItemIndex) {
                0 -> ProfileView(studentID = studentID,)
                1 -> MainScreen(navController = navController)
                2 -> AllRecordsScreen(navController)
            }
        }
    }
}
