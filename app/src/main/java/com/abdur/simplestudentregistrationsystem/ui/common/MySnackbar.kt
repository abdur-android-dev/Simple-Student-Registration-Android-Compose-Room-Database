package com.abdur.simplestudentregistrationsystem.ui.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun MySnackbar(
    scope:CoroutineScope,
    snackbarHostState: SnackbarHostState,
    msg : String,
    actionLabel : String,
    onAction:()->Unit
){
    scope.launch {
        snackbarHostState.currentSnackbarData?.dismiss()
        val snackebarResult = snackbarHostState.showSnackbar(
            message = msg,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )

        when(snackebarResult){
            SnackbarResult.ActionPerformed -> {
                onAction()
            }
            SnackbarResult.Dismissed ->{

            }
        }
    }
}