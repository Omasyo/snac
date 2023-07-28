package com.keetr.snac.baselineprofile

//import androidx.benchmark.macro.BaselineProfileMode
//import androidx.benchmark.macro.CompilationMode
//import androidx.benchmark.macro.FrameTimingMetric
//import androidx.benchmark.macro.StartupMode
//import androidx.benchmark.macro.StartupTimingMetric
//import androidx.benchmark.macro.junit4.MacrobenchmarkRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.filters.LargeTest
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class ScrollBenchmarks {
//
//    @get:Rule
//    val rule = MacrobenchmarkRule()
//
//    @Test
//    fun startupCompilationNone() =
//        benchmark(CompilationMode.None())
//
//    @Test
//    fun startupCompilationBaselineProfiles() =
//        benchmark(CompilationMode.Partial(BaselineProfileMode.Require))
//
//    private fun benchmark(compilationMode: CompilationMode) {
//        rule.measureRepeated(
//            packageName = "com.keetr.snac",
//            metrics = listOf(FrameTimingMetric()),
//            compilationMode = compilationMode,
//            startupMode = StartupMode.WARM,
//            iterations = 10,
//            setupBlock = {
//                pressHome()
//                startActivityAndWait()
//            },
//            measureBlock = {
//                waitForHomeContent()
//                scrollDiscoverScreen()
//            }
//        )
//    }
//}