package com.abdur.simplestudentregistrationsystem.ui.screens
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.abdur.simplestudentregistrationsystem.R
import com.abdur.simplestudentregistrationsystem.ui.theme.CardAllColor
import com.abdur.simplestudentregistrationsystem.ui.theme.CardColor
import com.abdur.studentroomdbproject.ui.navigation.Routes
import com.abdur.studentroomdbproject.ui.viewmodels.StudentViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AllRecordsScreen(navController: NavController){
    val studentViewModel: StudentViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()
    val studentState = studentViewModel.getAllStudents.collectAsState(initial = null)

    scope.launch {
        delay(100)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        if (studentState.value !=null){
            val students = studentState.value!!
            for(student in students){
                AllRecords(imageUri = student.picture, studentId = student.studentId, navController)
            }
        }else{
            Text(text = "Loading")
        }
    }
}
@Composable
fun AllRecords(imageUri:String, studentId:String, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (card,img,txtname) = createRefs()
        Card(colors = CardDefaults.cardColors(CardAllColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    navController.navigate(Routes.BOTTOM_SCREEN.replace("{studentID}", studentId))
                }
                .constrainAs(card) {
                    linkTo(parent.start, parent.end)
                })
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
                ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color = Color.White, shape = CircleShape)
                ){
                    val painter = rememberAsyncImagePainter(model = imageUri)
                    Image(painter = painter,
                        contentDescription = null,
                    )
                }
                Text(
                    text = studentId,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}