package com.ezz.contactsprovider.preference

import javax.inject.Inject
import javax.inject.Provider

internal interface ContactsSyncPreference {
    fun getLastSyncTimeStamp(): Long
    fun setHasSynced()

    class Factory @Inject constructor(private val map: MutableMap<Type, Provider<ContactsSyncPreference>>) {
        fun get(type: Type): ContactsSyncPreference = map[type]?.get()!!
    }

    enum class Type {
        All, Deleted, Updated
    }
}