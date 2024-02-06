package com.abdur.simplestudentregistrationsystem.di

import android.content.Context
import androidx.room.Room
import com.abdur.studentroomdbproject.data.StudentDatabase
import com.abdur.studentroomdbproject.repository.StudentDAO
import com.abdur.studentroomdbproject.repository.StudentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) : StudentDatabase{
        return Room.databaseBuilder(
            context,
            StudentDatabase::class.java,
            "student_database"
            ).build()
    }

    @Provides
    @Singleton
    fun provideStudentDao(studentDB : StudentDatabase) : StudentDAO{
        return studentDB.studentDAO()
    }

    @Provides
    @Singleton
    fun provideStudentRepository(studentDAO: StudentDAO):StudentRepository{
            return StudentRepository(studentDAO)
    }
}