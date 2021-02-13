package com.ezz.contactsprovider.di

import android.content.Context
import androidx.room.Room
import com.ezz.contactsprovider.db.ContactsDao
import com.ezz.contactsprovider.db.ContactsProviderDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@AppContext context: Context): ContactsProviderDatabase =
        Room.databaseBuilder(
            context,
            ContactsProviderDatabase::class.java, "ContactsProviderDatabase"
        ).build()

    @Provides
    fun contactsDao(database: ContactsProviderDatabase): ContactsDao = database.contactsDao()
}