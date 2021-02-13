package com.ezz.contactsprovider.di.modules

import android.content.ContentResolver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.ezz.contactsprovider.di.AppContext
import dagger.Module
import dagger.Provides

private const val SHARED_PREFERENCE_NAME = "contactsSharedPreference"

@Module(includes = [PreferenceModule::class, ContactsGetterModule::class, DatabaseModule::class])
class ProviderModule {
    @Provides
    fun providesSharedPreference(@AppContext context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)

    @Provides
    fun providesContentResolver(@AppContext context: Context): ContentResolver =
        context.contentResolver
}