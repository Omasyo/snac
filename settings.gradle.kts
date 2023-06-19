pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        includeBuild("build-logic")
        google()
        mavenCentral()
    }
}
rootProject.name = "Snac"
include("app")
include(":core:model")
include(":core:ui")
include(":feature:discover")
include(":feature:movie")
include(":core:common")
