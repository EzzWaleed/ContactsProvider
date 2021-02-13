package com.ezz.contactsprovider.di.modules

import com.ezz.contactsprovider.preference.AllContactsSyncPreferenceImpl
import com.ezz.contactsprovider.preference.ContactsSyncPreference
import com.ezz.contactsprovider.preference.DeletedContactsSyncPreferenceImpl
import com.ezz.contactsprovider.preference.UpdatedContactsSyncPreferenceImpl
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface PreferenceModule {

    @Binds
    @IntoMap
    @SyncPreferenceKey(ContactsSyncPreference.Type.All)
    fun bindsAllPreference(allContactsSyncPreferenceImpl: AllContactsSyncPreferenceImpl): ContactsSyncPreference

    @Binds
    @IntoMap
    @SyncPreferenceKey(ContactsSyncPreference.Type.Deleted)
    fun bindsDeletedPreference(deletedContactsSyncPreferenceImpl: DeletedContactsSyncPreferenceImpl): ContactsSyncPreference

    @Binds
    @IntoMap
    @SyncPreferenceKey(ContactsSyncPreference.Type.Updated)
    fun bindsUpdatedPreference(updatedContactsSyncPreferenceImpl: UpdatedContactsSyncPreferenceImpl): ContactsSyncPreference

}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
internal annotation class SyncPreferenceKey(val value: ContactsSyncPreference.Type)


