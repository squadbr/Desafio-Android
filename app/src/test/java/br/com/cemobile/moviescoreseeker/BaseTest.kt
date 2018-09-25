package br.com.cemobile.moviescoreseeker

import android.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cemobile.moviescoreseeker.di.appModule
import br.com.cemobile.moviescoreseeker.di.databaseModule
import com.google.gson.Gson
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import org.mockito.Mockito
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder

@RunWith(JUnit4::class)
open class BaseTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    val server: MockWebServer by lazy {
        MockWebServer()
    }

    @Before
    open fun `before each test`() {
        startKoin(listOf(appModule, databaseModule))
        server.start()
    }

    @After
    open fun `after each test`() {
        server.shutdown()
        closeKoin()
    }

    @Throws(IOException::class)
    fun readJsonFromFile(filename: String): String {
//        val reader = BufferedReader(InputStreamReader(FileInputStream(filename)))
        val reader = BufferedReader(InputStreamReader(javaClass.classLoader.getResourceAsStream(filename)))
        val builder = StringBuilder()
        var line = reader.readLine()
        while (line != null) {
            builder.append(line)
            line = reader.readLine()
        }

        return reader.toString()
    }

    inline fun <reified T : Any> mock(): T = Mockito.mock(T::class.java)

    inline fun <reified T : Any> mockFromJson(json: String): T
            = Gson().fromJson(json, T::class.java)

    fun await(millis: Long = 200) = Thread.sleep(millis)

}