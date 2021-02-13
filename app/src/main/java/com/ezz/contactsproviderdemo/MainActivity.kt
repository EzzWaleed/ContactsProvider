package com.ezz.contactsproviderdemo

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ezz.contactsprovider.ContactsProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

private const val PERMISSION_REQUEST_CODE = 122
private const val TAG = "MAIN_ACTIVITY"

class MainActivity : AppCompatActivity() {

    private val contactsProvider = ContactsProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), PERMISSION_REQUEST_CODE)
        }

        contactsProvider.fetchContacts().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
            it.map {
                Log.d(TAG, it.name)
            }
        }, {
            Log.e(TAG, it.localizedMessage)
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE)
            contactsProvider.subscribeToContactsUpdate()
        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}