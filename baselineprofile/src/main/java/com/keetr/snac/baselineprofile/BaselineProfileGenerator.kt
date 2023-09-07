package com.keetr.snac.baselineprofile

import android.view.KeyEvent
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until
import com.keetr.snac.core.model.SectionType
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern

/**
 * This test class generates a basic startup baseline profile for the target package.
 *
 * We recommend you start with this but add important user flows to the profile to improve their performance.
 * Refer to the [baseline profile documentation](https://d.android.com/topic/performance/baselineprofiles)
 * for more information.
 *
 * You can run the generator with the Generate Baseline Profile run configuration,
 * or directly with `generateBaselineProfile` Gradle task:
 * ```
 * ./gradlew :app:generateReleaseBaselineProfile -Pandroid.testInstrumentationRunnerArguments.androidx.benchmark.enabledRules=BaselineProfile
 * ```
 * The run configuration runs the Gradle task and applies filtering to run only the generators.
 *
 * Check [documentation](https://d.android.com/topic/performance/benchmarking/macrobenchmark-instrumentation-args)
 * for more information about available instrumentation arguments.
 *
 * After you run the generator, you can verify the improvements running the [StartupBenchmarks] benchmark.
 **/
@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect("com.keetr.snac") {
            // This block defines the app's critical user journey. Here we are interested in
            // optimizing for app startup. But you can also navigate and scroll
            // through your most important UI.

            // Start default activity for your app
            pressHome()
            startActivityAndWait()

            waitForHomeContent()

            scrollDiscoverScreen()
//            seekTvShowJourney()
            searchJourney()
        }
    }
}

fun MacrobenchmarkScope.waitForHomeContent() {
    device.wait(Until.hasObject(By.res("search_bar")), 10_000)
    device.wait(Until.hasObject(By.res("section_list")), 10_000)
    val sectionList = device.findObject(By.res("section_list"))
//    contentList.wait(Until.hasObject(By.res("snack_collection")), 10_000)

//    sectionList.run {
//        for (section in SectionType.values()) {
//            wait(Until.hasObject(By.res("${section}_carousel")), 10_000)
//        }
//    }
}

fun MacrobenchmarkScope.scrollDiscoverScreen() {
    var sectionList = device.findObject(By.res("section_list"))
    // Set gesture margin to avoid triggering gesture navigation
    sectionList.setGestureMargin(device.displayWidth / 5)
//    sectionList.scrollUntil(Direction.DOWN, Until.scrollFinished(Direction.DOWN))
    sectionList.fling(Direction.DOWN)

    //TODO: possible recomposition making section list stale?
    sectionList = device.findObject(By.res("section_list"))
    // Set gesture margin to avoid triggering gesture navigation
    sectionList.setGestureMargin(device.displayWidth / 5)
    sectionList.scrollUntil(Direction.UP, Until.scrollFinished(Direction.UP))
    sectionList.fling(Direction.UP)

    device.waitForIdle()
}

fun MacrobenchmarkScope.seekTvShowJourney() {
    val sectionList = device.findObject(By.res("section_list"))
    //TODO: sectionlist doesn't scroll to the end possible recompositon
//    sectionList.scrollUntil(Direction.DOWN, Until.findObject(By.res("${SectionType.TvTopRated}_carousel")))
    sectionList.fling(Direction.DOWN)
    device.waitForIdle()

    //TODO: to make GMDs work properly
    val topRatedTvList = sectionList.findObject(By.res(Pattern.compile(".+_carousel")))
//    val topRatedTvList = sectionList.findObject(By.res("${SectionType.TvTopRated}_carousel"))
    topRatedTvList.setGestureMargin(device.displayWidth / 5)
    topRatedTvList.fling(Direction.RIGHT)
    device.waitForIdle()

    val tv = topRatedTvList.findObject(By.clickable(true))
    tv.click()
    device.wait(Until.gone(By.res("section_list")), 10_000)

    device.pressBack()
    device.wait(Until.hasObject(By.res("section_list")), 10_000)
}

fun MacrobenchmarkScope.searchJourney() {
    val searchBar = device.findObject(By.res("search_bar"))
    searchBar.click()
    device.waitForIdle()

//    searchBar.text = "spider"
    device.pressKeyCodes(
        intArrayOf(
            KeyEvent.KEYCODE_A,
            KeyEvent.KEYCODE_S,
//            KeyEvent.KEYCODE_P,
//            KeyEvent.KEYCODE_I,
//            KeyEvent.KEYCODE_D
        )
    )
    searchBar.wait(Until.gone(By.res("loader")), 10_000)
    device.pressBack()

    val searchResults = searchBar.findObject(By.res("search_results"))
    searchResults.fling(Direction.DOWN)
//    device.waitForIdle()

    val result = searchResults.findObject(By.clickable(true))
    result.click()
    device.wait(Until.gone(By.res("search_bar")), 10_000)

//    device.pressBack()
//    device.pressBack()
//    device.waitForIdle()
}
