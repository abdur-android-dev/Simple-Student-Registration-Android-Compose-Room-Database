package com.abdur.studentroomdbproject.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val studentId: String,
    val studentName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val password: String,
    val picture: String
)