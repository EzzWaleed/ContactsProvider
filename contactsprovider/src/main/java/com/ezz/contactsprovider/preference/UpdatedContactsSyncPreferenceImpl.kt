package com.ezz.contactsprovider.preference

import android.content.SharedPreferences
import javax.inject.Inject

private const val TIME_STAMP_KEY = "UpdatedTimeStampKey"

internal class UpdatedContactsSyncPreferenceImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    ContactsSyncPreference {
    override fun getLastSyncTimeStamp(): Long = sharedPreferences.getLong(TIME_STAMP_KEY, 0)
    override fun setHasSynced() =
        sharedPreferences.edit().putLong(TIME_STAMP_KEY, System.currentTimeMillis()).apply()
}