package com.keetr.snac.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import org.junit.Rule
import org.junit.Test

class StartupProfileGenerator {
    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun startup() =
        baselineProfileRule.collect(
            packageName = "com.example.app",
            includeInStartupProfile = true
        ) {
            // This scenario just starts the activity and waits for it to draw
            // the first frame. If you have animations or async content in your
            // startup, wait for them with UiAutomator.
            startActivityAndWait()
        }
}