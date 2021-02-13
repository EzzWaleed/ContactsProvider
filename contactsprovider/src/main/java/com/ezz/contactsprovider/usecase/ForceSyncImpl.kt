package com.ezz.contactsprovider.usecase

import com.ezz.contactsprovider.contactsgetter.ContactsGetter
import com.ezz.contactsprovider.db.ContactsDao
import com.ezz.contactsprovider.di.modules.ContactsGetterModule
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject
import javax.inject.Named

internal class ForceSyncImpl @Inject constructor(
    @Named(ContactsGetterModule.ALL_KEY) private val contactsGetter: ContactsGetter,
    private val dao: ContactsDao
) : ForceSync {
    override fun invoke(): Completable = contactsGetter.getContacts().toList().flatMapCompletable {
        dao.deleteAllExcept(it.map {
                contact -> contact.toString()
        }).andThen(dao.insert(it))
    }
}