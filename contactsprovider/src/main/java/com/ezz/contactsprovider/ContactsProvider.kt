package com.ezz.contactsprovider

import android.content.ContentResolver
import android.content.Context
import android.provider.ContactsContract
import com.ezz.contactsprovider.di.ComponentWrapper
import com.ezz.contactsprovider.entities.Contact
import com.ezz.contactsprovider.observer.ContactObserver
import com.ezz.contactsprovider.usecase.GetContacts
import com.ezz.contactsprovider.utils.PermissionChecker
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ContactsProvider {
    @Inject
    internal lateinit var contactObserver: ContactObserver

    @Inject
    internal lateinit var getContacts: GetContacts

    @Inject
    internal lateinit var contentResolver: ContentResolver

    @Inject
    internal lateinit var permissionChecker: PermissionChecker

    init {
        ComponentWrapper.instance.inject(this)
    }

    fun fetchContacts(): Flowable<List<Contact>> = getContacts()

    fun subscribeToContactsUpdate() {
        if (permissionChecker.isContactsReadPermissionGranted()) {
            contentResolver.registerContentObserver(
                ContactsContract.Contacts.CONTENT_URI,
                true,
                contactObserver
            )
        }
    }

    companion object {
        fun init(context: Context) {
            ComponentWrapper.init(context)
        }
    }
}