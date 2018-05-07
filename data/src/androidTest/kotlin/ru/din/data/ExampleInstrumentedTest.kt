package ru.din.data

import android.support.test.InstrumentationRegistry.getTargetContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getTargetContext()
        assertEquals("ru.din.presentation.test", appContext.packageName)
    }
}
