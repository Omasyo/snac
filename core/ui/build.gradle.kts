plugins {
    id("snac.android.library")
    id("snac.android.compose")
}

android {
    namespace = "com.quitr.snac.core.ui"
}

dependencies {
    implementation(project(mapOf("path" to ":core:model")))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)
}