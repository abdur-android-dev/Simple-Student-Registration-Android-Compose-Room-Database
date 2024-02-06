package com.abdur.studentroomdbproject.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abdur.simplestudentregistrationsystem.ui.screens.AllRecordsScreen
import com.abdur.simplestudentregistrationsystem.ui.screens.BottomBarScreen
import com.abdur.studentroomdbproject.ui.navigation.Routes

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
        composable(Routes.HOME_SCREEN){
            MainScreen(navController)
        }
        composable(Routes.LOGIN_SCREEN) {
            SigningScreen(navController)
        }

        composable(Routes.SIGNUP_SCREEN) {
            SignupScreen(navController)
        }
        composable(Routes.BOTTOM_SCREEN) {backStackEntry ->
            val studentID = backStackEntry.arguments?.getString("studentID")?:"no student id found"
            BottomBarScreen(studentID, navController)
        }

        composable(Routes.PROFILE_SCREEN) {backStackEntry ->
            val studentID = backStackEntry.arguments?.getString("studentID")?:"no student id found"
            ProfileView(studentID)
        }

        composable(Routes.ALL_RECORD_SCREEN) {backStackEntry ->
            AllRecordsScreen(navController)
        }
    }
}