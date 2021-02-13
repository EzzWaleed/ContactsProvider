package com.ezz.contactsprovider

import android.provider.ContactsContract

object Constants {
    internal val CONTACT_PROJECTION_STRING = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
    )
    internal val DELETED_CONTACT_PROJECTION_STRING = arrayOf(
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP
    )

    internal const val CONTACT_TIME_STAMP_SELECTION_CLOSURE =
        "${ContactsContract.Contacts.CONTACT_LAST_UPDATED_TIMESTAMP} > ?"

    internal const val DELETED_CONTACT_TIME_STAMP_SELECTION_CLOSURE =
        "${ContactsContract.DeletedContacts.CONTACT_DELETED_TIMESTAMP} > ?"
}