package com.ezz.contactsprovider.usecase

import com.ezz.contactsprovider.contactsgetter.ContactsGetter
import com.ezz.contactsprovider.db.ContactsDao
import com.ezz.contactsprovider.di.modules.ContactsGetterModule
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import javax.inject.Named

internal class SyncUpdatesImpl @Inject constructor(
    @Named(ContactsGetterModule.Deleted_KEY) private val deletedContactsGetter: ContactsGetter,
    @Named(ContactsGetterModule.Updated_KEY) private val updatedContactsGetter: ContactsGetter,
    private val contactsDao: ContactsDao
) :
    SyncUpdates {
    override fun invoke(): Completable = updatedContactsGetter.getContacts().flatMapCompletable {
        contactsDao.insert(it)
    }.mergeWith(deletedContactsGetter.getContacts().flatMapCompletable {
        contactsDao.delete(it)
    })
}