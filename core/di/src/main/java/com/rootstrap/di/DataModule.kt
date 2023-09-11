package com.rootstrap.di

import com.rootstrap.data.network.ApiProvider
import com.rootstrap.data.network.AuthInterceptor
import com.rootstrap.data.network.HttpClientProvider
import com.rootstrap.data.network.HttpClientProviderImpl
import com.rootstrap.data.store.PreferencesDataStore
import com.rootstrap.data.utils.Constants.MegaBytes50
import com.rootstrap.data.utils.CoroutineErrorHandler
import com.rootstrap.domain.errors.ErrorHandler
import com.rootstrap.domain.errors.ErrorNotifier
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope
import org.koin.dsl.module

val dataModule = module {
    single { androidApplication().resources }
    single { ErrorNotifier() }
    single { createJson() }
    single { createPreferencesDataStore() }
    single { AuthInterceptor(get()) }
    single<HttpClientProvider> { createHttpClientProvider() }
    single { createApiProvider() }
    single { get<ApiProvider>().myApi }

    factory { Cache(androidApplication().cacheDir, MegaBytes50) }
    factory<ErrorHandler> {
        CoroutineErrorHandler(
            errorNotifier = get()
        )
    }
}

@OptIn(ExperimentalSerializationApi::class)
private fun createJson() = Json {
    explicitNulls = false
    ignoreUnknownKeys = true
    isLenient = true
    useAlternativeNames = false
}

private fun Scope.createApiProvider() = ApiProvider(
    json = get(),
    httpClientProvider = get()
)

private fun Scope.createPreferencesDataStore() = PreferencesDataStore(
    coroutineDispatcher = Dispatchers.IO,
    context = androidContext(),
    preferencesFileName = "app_prefs"
)

private fun Scope.createHttpClientProvider() =
    HttpClientProviderImpl(
        cache = get(),
        authInterceptor = get()
    )
