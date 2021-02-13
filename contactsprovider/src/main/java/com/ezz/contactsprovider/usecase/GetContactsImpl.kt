package com.ezz.contactsprovider.usecase

import android.provider.ContactsContract
import com.ezz.contactsprovider.db.ContactsDao
import com.ezz.contactsprovider.entities.Contact
import com.ezz.contactsprovider.preference.ContactsSyncPreference
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

internal class GetContactsImpl @Inject constructor(
    factory: ContactsSyncPreference.Factory,
    private val syncUpdates: SyncUpdates,
    private val forceSync: ForceSync,
    private val dao: ContactsDao
) : GetContacts {
    private val deletedContactsPreference = factory.get(ContactsSyncPreference.Type.Deleted)
    override fun invoke(): Flowable<List<Contact>> =
        if (System.currentTimeMillis()
                .minus(deletedContactsPreference.getLastSyncTimeStamp()) < ContactsContract.DeletedContacts.DAYS_KEPT_MILLISECONDS
        )
            syncUpdates().andThen(dao.getAll())
        else
            forceSync().andThen(dao.getAll())
}