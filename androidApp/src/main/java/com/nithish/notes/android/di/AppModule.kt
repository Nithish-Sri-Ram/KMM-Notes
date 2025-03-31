package com.nithish.notes.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.nithish.notes.data.note.SqlDelightNoteDataSource
import com.nithish.notes.database.AndroidDatabaseDriverFactory
import com.nithish.notes.database.NoteDatabase
import com.nithish.notes.domain.note.NoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return AndroidDatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }
}