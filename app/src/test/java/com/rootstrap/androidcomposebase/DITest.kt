package com.rootstrap.androidcomposebase

// import okhttp3.Cache
import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.rootstrap.di.dataModule
import com.rootstrap.di.useCasesModule
import com.rootstrap.yourAppName.di.appModule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

@org.junit.Ignore("Need to fix cache issue")
class DITest : KoinTest {

    private val applicationMock: Application = mockk()
    private val contextMock: Context = mockk()
    private val resourcesMock: Resources = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val mockProviderRule = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Before
    @OptIn(ExperimentalCoroutinesApi::class)
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun verifyDIModules() {
        every { applicationMock.applicationContext } returns contextMock
        every { applicationMock.resources } returns resourcesMock
        every { resourcesMock.getString(any()) } returns ""

        val app = koinApplication {
            androidContext(applicationMock)
            modules(appModule, dataModule, useCasesModule)
        }

        startKoin(app)

        app.checkModules()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }
}
