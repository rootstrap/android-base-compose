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

class PreferencesDataStore( // TODO set as singleton in Koin
    coroutineDispatcher: CoroutineDispatcher, // TODO inject Dispatcher.IO using Koin
    context: Context, // TODO inject using Koin
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "rootstrap_prefs", // TODO use you app's name,
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
