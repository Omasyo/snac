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
include(":core:data")
include(":core:database")
include(":core:model")
include(":core:network")
include(":core:ui")
include(":feature:discover")
include(":feature:movie")
include(":feature:people")
include(":feature:search")
include(":feature:tv")
