package com.abdur.studentroomdbproject.ui.screens

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.abdur.simplestudentregistrationsystem.R
import com.abdur.simplestudentregistrationsystem.ui.common.ToastMessage
import com.abdur.simplestudentregistrationsystem.ui.theme.CheckIconColor
import com.abdur.simplestudentregistrationsystem.ui.theme.CrossIconColor
import com.abdur.studentroomdbproject.ui.navigation.Routes
import com.abdur.studentroomdbproject.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SigningScreen(navController: NavController){
    val context = LocalContext.current
    val studentViewModel: StudentViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()

    val student by studentViewModel.student.collectAsState(null)
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        var txt_et_emails = remember {
            mutableStateOf("")
        }
        var txt_et_passwords = remember {
            mutableStateOf("")
        }

        var isEmailValid by remember { mutableStateOf(false) }

        val keyboardController = LocalSoftwareKeyboardController.current
        var isPasswordVisible by remember { mutableStateOf(false) }
        val (
            img_star,
            txt_title,
            txt_email,
            et_email,
            txt_password,
            et_password,
            btn_login,
            txt_already_account
            ) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.star8),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(0.15f)
                .constrainAs(img_star) {
                    linkTo(parent.top, parent.bottom, bias = 0.05f)
                    linkTo(parent.start, parent.end, bias = 0.85f)
                }
            )

        Text(
            text = "Log in",
            fontFamily = FontFamily(Font(
                R.font.poppins_bold,
                weight = FontWeight.Bold)),
            fontSize = 40.sp,
            modifier = Modifier
                .constrainAs(txt_title){
                    linkTo(img_star.bottom,parent.bottom, bias = 0.04f)
                    linkTo(parent.start,parent.end, bias = 0.1f)
                }
            )

        Text(
            text = "Email address",
            fontFamily = FontFamily(Font(
                R.font.inter,
                weight = FontWeight.Normal)),
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(txt_email){
                    linkTo(txt_title.bottom,parent.bottom, bias = 0.04f)
                    linkTo(parent.start,parent.end, bias = 0.1f)
                }
        )

        // Email TextFiled
        BasicTextField(
            value = txt_et_emails.value,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                txt_et_emails.value=it
                isEmailValid = isValidEmail(email = it)
            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(et_email) {
                    linkTo(txt_title.bottom, parent.bottom, bias = 0.1f)
                    linkTo(parent.start, parent.end)
                },decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        //.padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xffe4e4e4),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (txt_et_emails.value.isEmpty()) {
                        Text(
                            text = "Your email address",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF949494)
                        )
                    }
                    Icon(
                        painter = painterResource(id = if (isEmailValid) R.drawable.ic_check else R.drawable.ic_cross),
                        contentDescription = null,
                        tint = if(isEmailValid) CheckIconColor else CrossIconColor ,
                        modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    innerTextField()
                }
            })

        //Password Details
        Text(
            text = "Password",
            fontFamily = FontFamily(Font(
                R.font.inter,
                weight = FontWeight.Normal)),
            fontSize = 20.sp,
            modifier = Modifier
                .constrainAs(txt_password){
                    linkTo(et_email.bottom,parent.bottom, bias = 0.04f)
                    linkTo(parent.start,parent.end, bias = 0.1f)
                }
        )

        // Password TextFiled
        BasicTextField(
            value = txt_et_passwords.value,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            onValueChange = {
                txt_et_passwords.value =it
            }, modifier = Modifier
                .fillMaxWidth(0.9f)
                .constrainAs(et_password) {
                    linkTo(txt_password.bottom, parent.bottom, bias = 0.01f)
                    linkTo(parent.start, parent.end)
                },decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        //.padding(horizontal = 64.dp) // margin left and right
                        .fillMaxWidth()
                        .background(Color(0xFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xffe4e4e4),
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                ) {
                    if (txt_et_passwords.value.isEmpty()) {
                        Text(
                            text = "Password",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF949494)
                        )
                    }
                    val eyeIconRes = if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_close
                    Icon(
                        painter = painterResource(id = eyeIconRes),
                        contentDescription = null,
                        tint = Color.Gray ,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable {
                                isPasswordVisible = !isPasswordVisible
                            }
                    )
                    innerTextField()
                }
            })

        Button(
            onClick = {
                studentViewModel.loginStudent(txt_et_emails.value,txt_et_passwords.value)
                scope.launch {
                    delay(100)
                        if (student != null) {
                            navController.navigate(Routes.BOTTOM_SCREEN.replace("{studentID}", student!!.studentId))
                            ToastMessage(context, "Login Success")
                        } else {
                            ToastMessage(context, "Login Failed")
                        }
                    }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(shape = RoundedCornerShape(10.dp), color = Color.Black)
                .constrainAs(btn_login) {
                    linkTo(et_password.bottom, parent.bottom, bias = 0.2f)
                    linkTo(parent.start, parent.end)
                }
        ) {
            Text(
                text = "Log in",
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_semibold,
                        weight = FontWeight.SemiBold
                    )
                )
            )
        }

        Row ( horizontalArrangement = Arrangement.Center,modifier = Modifier
            .fillMaxWidth()
            .constrainAs(txt_already_account) {
                linkTo(btn_login.bottom, parent.bottom)
                linkTo(parent.start, parent.end)
            }){
            Text(
                text = "Don’t have an account?",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.inter))
                )
            Text(
                text = "Sign up",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.poppins_bold),
                ),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate(Routes.HOME_SCREEN) {
                            popUpTo(navController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                )
        }
    }
}


fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}