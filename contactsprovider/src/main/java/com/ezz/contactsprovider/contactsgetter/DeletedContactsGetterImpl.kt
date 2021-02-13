package com.ezz.contactsprovider.contactsgetter

import android.content.ContentResolver
import android.provider.ContactsContract
import com.ezz.contactsprovider.Constants
import com.ezz.contactsprovider.entities.Contact
import com.ezz.contactsprovider.preference.ContactsSyncPreference
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class DeletedContactsGetterImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    factory: ContactsSyncPreference.Factory

) : ContactsGetter {

    private val contactsSyncPreference =
        factory.get(ContactsSyncPreference.Type.Deleted)

    override fun getContacts(): Flowable<Contact> = Flowable.create({ emitter ->
        val cursor = contentResolver.query(
            ContactsContract.DeletedContacts.CONTENT_URI,
            Constants.DELETED_CONTACT_PROJECTION_STRING,
            Constants.DELETED_CONTACT_TIME_STAMP_SELECTION_CLOSURE,
            arrayOf(contactsSyncPreference.getLastSyncTimeStamp().toString()), null
        )
        cursor.use {
            if (it != null && it.count > 0) {
                while (it.moveToNext()) {
                    emitter.onNext(
                        Contact(
                            id = it.getString(it.getColumnIndex(ContactsContract.Contacts._ID)),
                            name = "",
                            lastUpdateTimeStamp = it.getLong(it.getColumnIndex(ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP))
                        )
                    )
                }
                contactsSyncPreference.setHasSynced()
                emitter.onComplete()
            } else {
                emitter.onComplete()
            }
        }
    }, BackpressureStrategy.BUFFER)
}