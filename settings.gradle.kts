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
include(":core:common")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":feature:discover")
include(":feature:movie")
include(":core:data")
include(":core:database")
include(":feature:people")
