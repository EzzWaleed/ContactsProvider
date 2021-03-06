package com.ezz.contactsprovider.di.modules

import com.ezz.contactsprovider.contactsgetter.AllContactsGetterImpl
import com.ezz.contactsprovider.contactsgetter.ContactsGetter
import com.ezz.contactsprovider.contactsgetter.DeletedContactsGetterImpl
import com.ezz.contactsprovider.contactsgetter.UpdatedContactsGetterImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
internal interface ContactsGetterModule {

    @Binds
    @Named(ALL_KEY)
    fun bindsAllGetter(allContactsGetterImpl: AllContactsGetterImpl): ContactsGetter

    @Binds
    @Named(Updated_KEY)
    fun bindsAllUpdated(updatedContactsGetterImpl: UpdatedContactsGetterImpl): ContactsGetter

    @Binds
    @Named(Deleted_KEY)
    fun bindsAllDeleted(deletedContactsGetterImpl: DeletedContactsGetterImpl): ContactsGetter


    companion object {
        const val ALL_KEY = "all"
        const val Updated_KEY = "updated"
        const val Deleted_KEY = "deleted"
    }
}