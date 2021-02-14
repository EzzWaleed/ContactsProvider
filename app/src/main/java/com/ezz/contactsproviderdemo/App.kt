package com.ezz.contactsproviderdemo

import android.app.Application
import com.ezz.contactsprovider.ContactsProvider

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ContactsProvider.init(this)
    }
}