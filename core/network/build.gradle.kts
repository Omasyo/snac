plugins {
    id("snac.android.library")
    id("snac.android.hilt")
    alias(libs.plugins.kotlin.plugin.serialization)
}

android {
    namespace = "com.quitr.snac.core.network"
}

dependencies {
    implementation(libs.ktor.auth)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.serialization.json)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("io.ktor:ktor-client-mock:2.3.2")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
}