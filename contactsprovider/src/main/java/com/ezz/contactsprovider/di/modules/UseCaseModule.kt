package com.ezz.contactsprovider.di.modules

import com.ezz.contactsprovider.usecase.*
import dagger.Binds
import dagger.Module

@Module
interface UseCaseModule {
    @Binds
    fun bindsSyncUpdates(syncUpdatesImpl: SyncUpdatesImpl): SyncUpdates
    @Binds
    fun bindsGetContacts(getContactsImpl: GetContactsImpl): GetContacts
    @Binds
    fun bindsForceSync(forceSyncImpl: ForceSyncImpl): ForceSync
}