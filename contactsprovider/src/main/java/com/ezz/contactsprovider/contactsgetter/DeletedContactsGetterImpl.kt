package com.ezz.contactsprovider.contactsgetter

import android.content.ContentResolver
import android.provider.ContactsContract
import com.ezz.contactsprovider.Constants
import com.ezz.contactsprovider.entities.Contact
import com.ezz.contactsprovider.preference.ContactsSyncPreference
import com.ezz.contactsprovider.utils.PermissionChecker
import com.ezz.contactsprovider.utils.ReadContactPermissionNotGranted
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

internal class DeletedContactsGetterImpl @Inject constructor(
    private val contentResolver: ContentResolver,
    factory: ContactsSyncPreference.Factory,
    private val permissionChecker: PermissionChecker
) : ContactsGetter {

    private val contactsSyncPreference =
        factory.get(ContactsSyncPreference.Type.Deleted)

    override fun getContacts(): Flowable<Contact> = Flowable.create({ emitter ->
        if (permissionChecker.isContactsReadPermissionGranted()) {
            val cursor = contentResolver.query(
                ContactsContract.DeletedContacts.CONTENT_URI,
                Constants.DELETED_CONTACT_PROJECTION_STRING,
                Constants.DELETED_CONTACT_TIME_STAMP_SELECTION_CLOSURE,
                arrayOf(contactsSyncPreference.getLastSyncTimeStamp().toString()), null
            )
            cursor.use {
                if (it != null) {
                    if (it.count > 0) {
                        while (it.moveToNext()) {
                            emitter.onNext(
                                Contact(
                                    id = it.getString(it.getColumnIndex(ContactsContract.DeletedContacts.CONTACT_ID)),
                                    name = "",
                                    lastUpdateTimeStamp = it.getLong(
                                        it.getColumnIndex(
                                            ContactsContract.DeletedContacts.CONTACT_DELETED_TIMESTAMP
                                        )
                                    )
                                )
                            )
                        }
                    }
                    contactsSyncPreference.setHasSynced()
                    emitter.onComplete()
                } else {
                    emitter.onComplete()
                }
            }
        } else
            emitter.onError(ReadContactPermissionNotGranted())
    }, BackpressureStrategy.BUFFER)
}