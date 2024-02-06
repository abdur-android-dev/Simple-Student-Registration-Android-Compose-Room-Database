package com.abdur.studentroomdbproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.abdur.simplestudentregistrationsystem.R
import com.abdur.studentroomdbproject.ui.navigation.Routes

@Composable
fun MainScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val (ic_image,
            txt_ttile,
            txt_description,
            btn_sign,
            btn_create_account) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.illustration),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(ic_image) {
                    linkTo(parent.start, parent.end)
                    linkTo(parent.top, parent.bottom, bias = 0.3f)
                }
        )
        Text(
            text = "Student Registration",
            fontSize = 35.sp,
            fontFamily = FontFamily(
                Font(
                    R.font.poppins_bold,
                    weight = FontWeight.Bold,
                    style = FontStyle.Normal
                )
            ),
            modifier = Modifier
                .constrainAs(txt_ttile) {
                    linkTo(ic_image.bottom, parent.bottom, bias = 0.1f)
                    linkTo(parent.start, parent.end)
                }
        )
        //Description Text
        Text(
            text = "Simple Student registration app with few database CRUD using Jetpack Compose",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(
                Font(
                    R.font.inter,
                    weight = FontWeight.Normal,
                    style = FontStyle.Normal
                )
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .constrainAs(txt_description) {
                    linkTo(txt_ttile.bottom, parent.bottom, bias = 0.0f)
                    linkTo(parent.start, parent.end)
                }
        )
        // Sign in button
        Button(
            onClick = {
                navController.navigate("SigningScreen")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(shape = RoundedCornerShape(10.dp), color = Color.Black)
                .constrainAs(btn_sign) {
                    linkTo(txt_description.bottom, parent.bottom, bias = 0.1f)
                    linkTo(parent.start, parent.end)
                }
        ) {
            Text(
                text = "Sign In",
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_semibold,
                        weight = FontWeight.SemiBold
                    )
                )
            )
        }

        //Create button
        Button(
            onClick = {
                      navController.navigate(Routes.SIGNUP_SCREEN)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(2.dp, Color.Black, RoundedCornerShape(8.dp))
                .constrainAs(btn_create_account) {
                    linkTo(btn_sign.bottom, parent.bottom, bias = 0.1f)
                    linkTo(parent.start, parent.end)
                }
        ) {
            Text(
                text = "Create account",
                color = Color.Black,
                fontSize = 18.sp,
                fontFamily = FontFamily(
                    Font(
                        R.font.inter_semibold,
                        weight = FontWeight.SemiBold
                    )
                )
            )
        }

    }
}