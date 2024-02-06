package com.abdur.studentroomdbproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.abdur.simplestudentregistrationsystem.R
import com.abdur.simplestudentregistrationsystem.ui.theme.CardColor
import com.abdur.studentroomdbproject.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProfileView(studentID:String) {
    val studentViewModel: StudentViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    studentViewModel.getStudentById(studentID)
    val student by studentViewModel.student.collectAsState(null)
    scope.launch {
        delay(500)
    }

    if (student != null) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val (
                topBox,
                profileImageBox,
                txt_name,
                txt_id,
                txt_phoneNumber,
                phone_Card,
                txt_emailAddress,
                email_Card
            ) = createRefs()
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Black)
                .fillMaxHeight(0.2f)
                .constrainAs(topBox) {
                    top.linkTo(parent.top)
                    linkTo(parent.start, parent.end)
                })
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(color = Color.White, shape = CircleShape)
                    .constrainAs(profileImageBox) {
                        linkTo(parent.top, parent.bottom, bias = 0.13f)
                        linkTo(parent.start, parent.end)
                    }
            )
            {
                val painter = rememberAsyncImagePainter(model = student?.picture)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = student!!.studentName,
                fontFamily = FontFamily(Font(R.font.poppins_bold, weight = FontWeight.Bold)),
                fontSize = 18.sp,
                modifier = Modifier.constrainAs(txt_name) {
                    linkTo(profileImageBox.bottom, parent.bottom, bias = 0.00f)
                    linkTo(parent.start, parent.end)
                }
            )

            Text(
                text = student!!.studentId,
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium,
                        weight = FontWeight.Medium
                    )
                ),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txt_id) {
                    linkTo(txt_name.bottom, parent.bottom, bias = 0.00f)
                    linkTo(parent.start, parent.end)
                }
            )
            //Text Phone Number
            Text(
                text = "Phone Number",
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium,
                        weight = FontWeight.Medium
                    )
                ),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txt_phoneNumber) {
                    linkTo(txt_name.bottom, parent.bottom, bias = 0.2f)
                    linkTo(parent.start, parent.end, bias = 0.1f)
                }
            )
            Card(colors = CardDefaults.cardColors(CardColor),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.05f)
                    .constrainAs(phone_Card) {
                        linkTo(txt_phoneNumber.bottom, parent.bottom, bias = 0.0f)
                        linkTo(parent.start, parent.end)
                    }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 20.dp),
                ) {
                    Text(
                        text = student!!.phoneNumber,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppins_medium,
                                weight = FontWeight.Medium
                            )
                        )
                    )
                }
            }

            //Text Phone Number
            Text(
                text = "Email Address",
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_medium,
                        weight = FontWeight.Medium
                    )
                ),
                fontSize = 16.sp,
                modifier = Modifier.constrainAs(txt_emailAddress) {
                    linkTo(phone_Card.bottom, parent.bottom, bias = 0.1f)
                    linkTo(parent.start, parent.end, bias = 0.1f)
                }
            )

            Card(colors = CardDefaults.cardColors(CardColor),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.05f)
                    .constrainAs(email_Card) {
                        linkTo(txt_emailAddress.bottom, parent.bottom, bias = 0.0f)
                        linkTo(parent.start, parent.end)
                    }) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 20.dp),
                ) {
                    Text(
                        text = student!!.emailAddress,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppins_medium,
                                weight = FontWeight.Medium
                            )
                        )
                    )
                }
            }
        }

    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Loading")
        }
    }
}
