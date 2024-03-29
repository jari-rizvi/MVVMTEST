package com.ingenious.betterworld.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import com.ingenious.betterworld.constants.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreProvider(context: Context) {

    //Create the dataStore
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = AppConstants.DataStore.DATA_STORE_NAME)

    //Create some keys
    companion object {
        val IS_LOCALIZATION_KEY = booleanPreferencesKey(AppConstants.DataStore.LOCALIZATION_KEY_NAME)
        val USER_NAME_KEY = stringPreferencesKey(AppConstants.DataStore.USER_NAME_KEY)
        val CONTINUE_KEY = booleanPreferencesKey(AppConstants.DataStore.CONTINUE_KEY)
    }

    //Store data
    suspend fun storeData(isLocalizationKey: Boolean, name: String) {
        dataStore.edit {
            it[IS_LOCALIZATION_KEY] = isLocalizationKey
            it[USER_NAME_KEY] = name
        }
    }

    //Create an Localization flow
    val localizationFlow: Flow<Boolean> = dataStore.data.map {
        it[IS_LOCALIZATION_KEY] ?: false
    }

}