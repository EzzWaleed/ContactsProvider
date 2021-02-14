package com.ezz.contactsprovider.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezz.contactsprovider.entities.Contact

@Database(entities = [Contact::class], version = 1)
internal abstract class ContactsProviderDatabase : RoomDatabase() {
    abstract fun contactsDao(): ContactsDao
}