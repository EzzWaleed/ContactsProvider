package com.ezz.contactsprovider.observer

import android.database.ContentObserver
import android.net.Uri
import android.util.Log
import com.ezz.contactsprovider.usecase.SyncUpdates
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

private const val TAG = "CONTACT_OBSERVER"

internal class ContactObserver @Inject constructor(private val syncUpdates: SyncUpdates) :
    ContentObserver(null) {

    private val syncSubject = PublishSubject.create<Any>()

    init {
        syncSubject.switchMapCompletable {
            syncUpdates().subscribeOn(Schedulers.io())
        }.subscribeOn(Schedulers.io()).subscribe({
            Log.d(TAG, "Sync Done")
        }, {
            Log.e(TAG, it.localizedMessage ?: "No Error Presented")
        })
    }

    override fun onChange(selfChange: Boolean, uri: Uri?, flags: Int) {
        super.onChange(selfChange, uri, flags)
        syncSubject.onNext(Any())
    }
}