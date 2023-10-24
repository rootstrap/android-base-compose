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
import java.io.IOException

class PreferencesDataStore(
    coroutineDispatcher: CoroutineDispatcher,
    context: Context,
    preferencesFileName: String
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = preferencesFileName,
        scope = CoroutineScope(coroutineDispatcher + SupervisorJob())
    )

    private val dataStore = context.dataStore

    val apiToken = getValue(API_TOKEN)

    suspend fun saveApiToken(token: String?) {
        if (token.isNullOrBlank()) {
            throw InvalidTokenException()
        }
        setValue(API_TOKEN, token)
    }

    suspend fun removeApiToken() {
        removeValue(API_TOKEN)
    }

    private fun getValue(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.map { preferences -> preferences[key].orEmpty() }
    }

    private suspend fun removeValue(key: Preferences.Key<String>) {
        dataStore.edit { preferences -> preferences.remove(key) }
    }

    private suspend fun setValue(key: Preferences.Key<String>, value: String) {
        try {
            dataStore.edit {
                it[key] = value
            }
        } catch (_: IOException) {
            throw WritingDataException()
        } catch (_: Exception) {
            throw InvalidValueException()
        }
    }

    private object Keys {
        val API_TOKEN = stringPreferencesKey("api_token")
    }
}
