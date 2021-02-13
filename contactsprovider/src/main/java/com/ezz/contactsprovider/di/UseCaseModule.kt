package com.ezz.contactsprovider.di

import com.ezz.contactsprovider.usecase.*
import dagger.Module

@Module
interface UseCaseModule {
    fun bindsSyncUpdates(syncUpdatesImpl: SyncUpdatesImpl): SyncUpdates
    fun bindsGetContacts(getContactsImpl: GetContactsImpl): GetContacts
    fun bindsForceSync(forceSyncImpl: ForceSyncImpl): ForceSync
}