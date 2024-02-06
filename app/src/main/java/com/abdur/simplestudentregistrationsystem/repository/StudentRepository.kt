package com.abdur.studentroomdbproject.repository

import com.abdur.studentroomdbproject.data.model.Student
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class StudentRepository @Inject constructor(private val studentDAO: StudentDAO) {

    suspend fun insertStudent(student: Student) {
        studentDAO.insertStudent(student)
    }

    suspend fun getStudentById(id: String): Student {
        return studentDAO.getStudentById(id = id)
    }

    suspend fun loginStudent(email: String, password:String): Student {
        return studentDAO.loginStudent(email,password)
    }


    fun getAllStudents() : Flow<List<Student>>{
        return studentDAO.getAllStudents()
    }
}