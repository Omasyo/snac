plugins {
    id("snac.android.feature")
}

android {
    namespace = "com.quitr.snac.feature.discover"
}
dependencies {
    implementation(libs.androidx.paging.compose)
}
