import java.io.FileInputStream
import java.util.Properties

plugins {
    id("snac.jvm.library")
    alias(libs.plugins.kotlin.plugin.serialization)
}

dependencies {
    implementation(libs.ktor.auth)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.core)
    implementation(libs.ktor.cio)
    implementation(libs.ktor.serialization.json)
}