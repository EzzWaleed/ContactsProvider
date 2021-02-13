package com.ezz.contactsprovider.usecase

import com.ezz.contactsprovider.entities.Contact
import io.reactivex.rxjava3.core.Flowable

interface GetContacts : () -> Flowable<List<Contact>>