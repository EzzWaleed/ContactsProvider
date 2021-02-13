package com.ezz.contactsprovider.di

import com.ezz.contactsprovider.usecase.SyncUpdates
import com.ezz.contactsprovider.usecase.SyncUpdatesImpl
import dagger.Module

@Module
interface UseCaseModule {
    fun bindsSyncUpdates(syncUpdatesImpl: SyncUpdatesImpl): SyncUpdates
}