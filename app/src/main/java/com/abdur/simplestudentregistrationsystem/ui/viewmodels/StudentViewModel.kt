package com.abdur.studentroomdbproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.studentroomdbproject.data.model.Student
import com.abdur.studentroomdbproject.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(private val repository: StudentRepository) : ViewModel() {

    private val _student = MutableStateFlow<Student?>(null)
    val student: StateFlow<Student?> get() = _student

    val getAllStudents = repository.getAllStudents()

    fun insertStudent(student: Student){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStudent(student)
        }
    }

    fun getStudentById(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            _student.value = repository.getStudentById(id)
        }
    }

    fun loginStudent(email : String,password : String){
        viewModelScope.launch(Dispatchers.IO) {
           val result = repository.loginStudent(email,password)
            if (result !=null){
                _student.value= result
            }else{
                _student.value=null
            }
        }
    }

    //private val _signupResult = MutableStateFlow<SignupResult?>(null)
    //val signupResult: StateFlow<SignupResult?> get() = _signupResult

    //fun clearSignupResult() {
      //  _signupResult.value = null
    //}
/*
    fun signUp(
        studentId: String,
        studentName: String,
        phoneNumber: String,
        emailAddress: String,
        password: String,
        picture: String
    ) {
        viewModelScope.launch {
            try {
                val student = Student(
                    studentId = studentId,
                    studentName = studentName,
                    phoneNumber = phoneNumber,
                    emailAddress = emailAddress,
                    password = password,
                    picture = picture
                )
                repository.insertStudent(student)
                _signupResult.value = SignupResult.Success
            }catch (e: Exception) {
                _signupResult.value = SignupResult.Error(e.message ?: "An error occurred")
            }
        }
    }

    */
}
/*
sealed class SignupResult {
    object Success : SignupResult()
    data class Error(val message: String) : SignupResult()
}
 */