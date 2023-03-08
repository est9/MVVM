package com.example.mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm.data.database.dao.QuoteDao
import com.example.mvvm.data.database.entities.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteDataBase: RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}