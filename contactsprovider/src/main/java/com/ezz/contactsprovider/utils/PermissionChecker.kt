package com.ezz.contactsprovider.utils

import android.content.Context
import com.ezz.contactsprovider.di.AppContext
import javax.inject.Inject

class PermissionChecker @Inject constructor(@AppContext private val context: Context) {
    fun isContactsReadPermissionGranted(): Boolean = context.isReadContactsPermitted()
}