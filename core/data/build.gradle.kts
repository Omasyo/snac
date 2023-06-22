plugins {
    id("snac.android.library")
}

android {
    namespace = "com.quitr.snac.core.data"
}

dependencies {
    implementation(project(mapOf("path" to ":core:model")))
    implementation(project(mapOf("path" to ":core:network")))
}