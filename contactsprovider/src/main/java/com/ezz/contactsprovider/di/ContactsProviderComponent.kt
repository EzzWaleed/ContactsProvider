package com.ezz.contactsprovider.di

import android.content.Context
import com.ezz.contactsprovider.ContactsProvider
import com.ezz.contactsprovider.di.modules.ProviderModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ProviderModule::class])
@Singleton
internal interface ContactsProviderComponent {

    fun inject(contactsProvider: ContactsProvider)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsAppContext(@AppContext context: Context): Builder
        fun build(): ContactsProviderComponent
    }

}

internal class ComponentWrapper {
    companion object {
        private var _component: ContactsProviderComponent? = null
        val instance: ContactsProviderComponent
            get() {
                if (_component == null) throw IllegalStateException("Must call ComponentWrapper.init() first")
                return _component!!
            }

        fun init(
            context: Context
        ): ContactsProviderComponent {
            if (_component != null) throw IllegalStateException("init can only be called once")
            _component = DaggerContactsProviderComponent.builder()
                .bindsAppContext(context).build()
            return instance
        }
    }
}