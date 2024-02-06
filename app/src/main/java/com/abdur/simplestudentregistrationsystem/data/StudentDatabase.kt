package com.abdur.studentroomdbproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdur.studentroomdbproject.data.model.Student
import com.abdur.studentroomdbproject.repository.StudentDAO

@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){
    abstract fun studentDAO(): StudentDAO
}