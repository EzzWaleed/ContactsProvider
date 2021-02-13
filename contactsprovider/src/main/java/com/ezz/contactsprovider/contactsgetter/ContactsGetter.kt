package com.ezz.contactsprovider.contactsgetter

import com.ezz.contactsprovider.entities.Contact
import io.reactivex.rxjava3.core.Flowable

interface ContactsGetter {
    fun getContacts(): Flowable<Contact>
}