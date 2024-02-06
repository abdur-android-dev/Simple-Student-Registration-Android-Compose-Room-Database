package com.abdur.simplestudentregistrationsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.abdur.simplestudentregistrationsystem.ui.theme.SimpleStudentRegistrationSystemTheme
import com.abdur.studentroomdbproject.ui.screens.App
import com.abdur.studentroomdbproject.ui.screens.SignupScreen
import com.abdur.studentroomdbproject.ui.viewmodels.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleStudentRegistrationSystemTheme {
                App()
                //DisplayRecords()
            }
        }
    }
}