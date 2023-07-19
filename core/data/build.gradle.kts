plugins {
    id("snac.android.library")
    id("snac.android.hilt")
}

android {
    namespace = "com.keetr.snac.core.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))

    implementation(libs.androidx.paging.compose)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}