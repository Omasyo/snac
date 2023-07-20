plugins {
    id("snac.android.library")
    id("snac.android.compose")
}

android {
    namespace = "com.keetr.snac.core.ui"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)
    implementation(libs.androidx.paging.compose)
//    implementation("androidx.compose.animation:animation-graphics")
}