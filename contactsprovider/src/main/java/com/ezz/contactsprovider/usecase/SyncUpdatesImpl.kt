package com.ezz.contactsprovider.usecase

import com.ezz.contactsprovider.contactsgetter.ContactsGetter
import com.ezz.contactsprovider.db.ContactsDao
import com.ezz.contactsprovider.di.ContactsGetterModule
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import javax.inject.Named

class SyncUpdatesImpl @Inject constructor(
    @Named(ContactsGetterModule.Deleted_KEY) private val deletedContactsGetter: ContactsGetter,
    @Named(ContactsGetterModule.Deleted_KEY) private val updatedContactsGetter: ContactsGetter,
    private val contactsDao: ContactsDao
) :
    SyncUpdates {
    override fun invoke(): Completable = deletedContactsGetter.getContacts().flatMapCompletable {
        contactsDao.delete(it)
    }.mergeWith(updatedContactsGetter.getContacts().flatMapCompletable { contactsDao.insert(it) })
}