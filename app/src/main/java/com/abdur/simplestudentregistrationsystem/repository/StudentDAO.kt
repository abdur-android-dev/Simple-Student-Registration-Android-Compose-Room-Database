package com.abdur.studentroomdbproject.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abdur.studentroomdbproject.data.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Query("SELECT * FROM students WHERE studentId = :id")
    suspend fun getStudentById(id: String): Student

    @Query("SELECT * FROM students WHERE emailAddress = :email AND password = :password")
    suspend fun loginStudent(email: String, password:String): Student

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>
}