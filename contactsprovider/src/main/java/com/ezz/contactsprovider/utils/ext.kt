package com.ezz.contactsprovider.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

internal fun Context.isReadContactsPermitted(): Boolean =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }