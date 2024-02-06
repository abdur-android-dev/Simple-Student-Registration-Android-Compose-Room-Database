package com.abdur.simplestudentregistrationsystem.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String? = null,
    val icon: ImageVector
) {
    object ProfileScreen : BottomNavItem(
        title = "Profile",
        icon = Icons.Outlined.Person
    )

    object HomeScreen : BottomNavItem(
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object AllScreen : BottomNavItem(
        title = "All Record",
        icon = Icons.Outlined.List
    )
}