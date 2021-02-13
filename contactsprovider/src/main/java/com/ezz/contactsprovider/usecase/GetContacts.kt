package com.ezz.contactsprovider.usecase

import com.ezz.contactsprovider.entities.Contact
import io.reactivex.rxjava3.core.Flowable

internal interface GetContacts : () -> Flowable<List<Contact>>