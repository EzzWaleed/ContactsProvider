package com.ezz.contactsprovider.di

import com.ezz.contactsprovider.preference.ContactsSyncPreference
import dagger.MapKey

/**
 * Created by Ezz Waleed on 07,March,2019
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class SyncPreferenceKey(val value: ContactsSyncPreference.Type)
