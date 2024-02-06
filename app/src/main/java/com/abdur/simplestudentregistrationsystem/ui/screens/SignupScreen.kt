package com.abdur.studentroomdbproject.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.abdur.simplestudentregistrationsystem.R
import com.abdur.simplestudentregistrationsystem.ui.common.ToastMessage
import com.abdur.simplestudentregistrationsystem.ui.theme.CheckIconColor
import com.abdur.simplestudentregistrationsystem.ui.theme.CrossIconColor
import com.abdur.studentroomdbproject.data.model.Student
import com.abdur.studentroomdbproject.ui.navigation.Routes
import com.abdur.studentroomdbproject.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignupScreen(navController: NavController) {
    val studentViewModel:StudentViewModel = hiltViewModel()
    val context = LocalContext.current
    //val signupResult by studentViewModel.signupResult.collectAsState()

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {

            val (
                img_star,
                txt_title,
                txt_name,
                et_name,
                txt_id,
                et_id,
                txt_email,
                et_email,
                txt_password,
                et_password,
                txt_phone,
                et_phone,
                txt_picture,
                btn_signup
            ) = createRefs()

            val imageUrl = rememberSaveable { mutableStateOf("") }
            val painter = rememberAsyncImagePainter(
                imageUrl.value.ifEmpty {
                    R.drawable.ic_person
                }
            )
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(context.contentResolver, uri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    val tempUri: Uri = getImageUriFromBitmap(context.applicationContext, bitmap)
                    imageUrl.value = tempUri.toString()
                }
            }
            var txt_et_name = remember {
                mutableStateOf("")
            }
            var txt_et_id = remember { mutableStateOf("") }
            var txt_et_email = remember { mutableStateOf("") }
            var txt_et_password = remember { mutableStateOf("") }
            var txt_et_phone = remember { mutableStateOf("") }

            var isEmailValid by remember { mutableStateOf(false) }

            val keyboardController = LocalSoftwareKeyboardController.current
            var isPasswordVisible by remember { mutableStateOf(false) }

            Image(
                painter = painterResource(id = R.drawable.star8),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(0.15f)
                    .constrainAs(img_star) {
                        linkTo(parent.top, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.85f)
                    }
            )

            Text(
                text = "Sign up",
                fontFamily = FontFamily(
                    Font(
                        R.font.poppins_bold,
                        weight = FontWeight.Bold
                    )
                ),
                fontSize = 40.sp,
                modifier = Modifier
                    .constrainAs(txt_title) {
                        linkTo(img_star.bottom, parent.bottom, bias = 0.00f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )
            // Name TextFiled Details
            Text(
                text = "Student Name",
                fontFamily = FontFamily(
                    Font(
                        R.font.inter,
                        weight = FontWeight.Normal
                    )
                ),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(txt_name) {
                        linkTo(txt_title.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )

            BasicTextField(
                value = txt_et_name.value,
                keyboardOptions = KeyboardOptions.Default
                    .copy(
                        imeAction = ImeAction.Next
                    ),
                onValueChange = {
                    txt_et_name.value = it
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(et_name) {
                        linkTo(txt_name.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end)
                    },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xffe4e4e4),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (txt_et_name.value.isEmpty()) {
                            Text(
                                text = "Your name",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF949494)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            // ID TextFiled Details
            Text(
                text = "Student ID",
                fontFamily = FontFamily(
                    Font(
                        R.font.inter,
                        weight = FontWeight.Normal
                    )
                ),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(txt_id) {
                        linkTo(et_name.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )
            BasicTextField(
                value = txt_et_id.value,
                keyboardOptions = KeyboardOptions.Default
                    .copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                onValueChange = {
                    txt_et_id.value = it
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(et_id) {
                        linkTo(txt_id.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end)
                    },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xffe4e4e4),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (txt_et_id.value.isEmpty()) {
                            Text(
                                text = "Student ID",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF949494)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            // Phone Number TextFiled Details
            Text(
                text = "Phone Number",
                fontFamily = FontFamily(
                    Font(
                        R.font.inter,
                        weight = FontWeight.Normal
                    )
                ),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(txt_phone) {
                        linkTo(et_id.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )

            BasicTextField(
                value = txt_et_phone.value,
                keyboardOptions = KeyboardOptions.Default
                    .copy(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                onValueChange = {
                    txt_et_phone.value = it
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(et_phone) {
                        linkTo(txt_phone.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end)
                    },
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 10.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xffe4e4e4),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        if (txt_et_phone.value.isEmpty()) {
                            Text(
                                text = "Phone number",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF949494)
                            )
                        }
                        innerTextField()
                    }
                }
            )

            // Email Address TextFiled Details
            Text(
                text = "Student Email",
                fontFamily = FontFamily(
                    Font(
                        R.font.inter,
                        weight = FontWeight.Normal
                    )
                ),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(txt_email) {
                        linkTo(et_phone.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )

            BasicTextField(
                value = txt_et_email.value,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                onValueChange = {
                    txt_et_email.value = it
                    isEmailValid = isValidEmail(email = it)
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(et_email) {
                        linkTo(txt_email.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end)
                    }, decorationBox = { innerTextField ->
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
                        if (txt_et_email.value.isEmpty()) {
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
                            tint = if (isEmailValid) CheckIconColor else CrossIconColor,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                        innerTextField()
                    }
                })

            //Password Details
            Text(
                text = "Password",
                fontFamily = FontFamily(
                    Font(
                        R.font.inter,
                        weight = FontWeight.Normal
                    )
                ),
                fontSize = 14.sp,
                modifier = Modifier
                    .constrainAs(txt_password) {
                        linkTo(et_email.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end, bias = 0.1f)
                    }
            )

            // Password TextFiled
            BasicTextField(
                value = txt_et_password.value,
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
                    txt_et_password.value = it
                }, modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .constrainAs(et_password) {
                        linkTo(txt_password.bottom, parent.bottom, bias = 0.01f)
                        linkTo(parent.start, parent.end)
                    }, decorationBox = { innerTextField ->
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
                        if (txt_et_password.value.isEmpty()) {
                            Text(
                                text = "Password",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color(0xFF949494)
                            )
                        }
                        val eyeIconRes =
                            if (isPasswordVisible) R.drawable.ic_eye_open else R.drawable.ic_eye_close
                        Icon(
                            painter = painterResource(id = eyeIconRes),
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    isPasswordVisible = !isPasswordVisible
                                }
                        )
                        innerTextField()
                    }
                })

            Row(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(txt_picture) {
                    linkTo(et_password.bottom, parent.bottom, bias = 0.01f)
                    linkTo(parent.start, parent.end, bias = 0.1f)
                }) {
                Column {
                    Text(
                        text = "Profile Picture",
                        fontFamily = FontFamily(
                            Font(
                                R.font.inter,
                                weight = FontWeight.Normal
                            )
                        ),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                    )

                    Button(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .padding(start = 20.dp, top = 10.dp)
                            .background(shape = RoundedCornerShape(10.dp), color = Color.Black)

                    ) {
                        Text(
                            text = "Browse",
                            fontSize = 16.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.inter_semibold
                                )
                            )
                        )
                    }

                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .size(100.dp)
                            .padding(top = 10.dp)
                    )
                }
            }

            Button(
                onClick = {
                        studentViewModel.insertStudent(
                            Student(
                                studentId = txt_et_id.value,
                                studentName = txt_et_name.value,
                                phoneNumber = txt_et_phone.value,
                                emailAddress = txt_et_email.value,
                                password = txt_et_password.value,
                                picture = imageUrl.value
                            )
                        )
                    navController.navigate(Routes.PROFILE_SCREEN.replace("{studentID}", txt_et_id.value))

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(shape = RoundedCornerShape(10.dp), color = Color.Black)
                    .constrainAs(btn_signup) {
                        linkTo(txt_picture.bottom, parent.bottom, bias = 0.2f)
                        linkTo(parent.start, parent.end)
                    }
            ) {
                Text(
                    text = "Sign up",
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

fun getImageUriFromBitmap(context: Context?, bitmap: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(context!!.contentResolver, bitmap, "File", null)
    return Uri.parse(path.toString())
}

