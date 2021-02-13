package com.ezz.contactsprovider.db

import androidx.room.*
import com.ezz.contactsprovider.entities.Contact
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ContactsDao {
    @Query("SELECT * FROM CONTACT")
    fun getAll(): Flowable<List<Contact>>

    @Delete
    fun delete(contact: Contact): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: List<Contact>): Completable

    @Query("DELETE FROM CONTACT WHERE id NOT IN (:ids)")
    fun deleteAllExcept(ids: List<String>): Completable
}