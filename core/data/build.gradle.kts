plugins {
    id("snac.android.library")
}

android {
    namespace = "com.quitr.snac.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.androidx.paging.compose)
}