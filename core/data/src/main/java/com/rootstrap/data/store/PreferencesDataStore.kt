package com.rootstrap.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rootstrap.data.store.PreferencesDataStore.Keys.API_TOKEN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataStore(
    coroutineDispatcher: CoroutineDispatcher,
    context: Context,
    preferencesFileName: String,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = preferencesFileName,
        scope = CoroutineScope(coroutineDispatcher + SupervisorJob())
    )

    private val dataStore = context.dataStore

    val apiToken: Flow<String> = dataStore.data.map {
        it[API_TOKEN].orEmpty()
    }

    suspend fun setApiToken(token: String) {
        dataStore.edit { it[API_TOKEN] = token }
    }

    private object Keys {
        val API_TOKEN = stringPreferencesKey("api_token")
    }

}
