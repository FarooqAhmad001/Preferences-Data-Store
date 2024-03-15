package com.example.datastoredemo.di

import com.example.datastoredemo.data.local.PreferenceDataStoreHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DIComponent: KoinComponent {

    // utils
    val dataStore by inject<PreferenceDataStoreHelper>()

}