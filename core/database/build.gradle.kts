plugins {
    id("snac.android.library")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.quitr.snac.core.database"
}

dependencies {
//    ksp(libs.androidx.room.compiler)
}