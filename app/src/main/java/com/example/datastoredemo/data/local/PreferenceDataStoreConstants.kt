package com.example.datastoredemo.data.local

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceDataStoreConstants {
    val NAME_KEY = stringPreferencesKey("NAME_KEY")
    val AGE_KEY = intPreferencesKey("AGE_KEY")
    val MOBILE_NUMBER = stringPreferencesKey("MOBILE_NUMBER")
}