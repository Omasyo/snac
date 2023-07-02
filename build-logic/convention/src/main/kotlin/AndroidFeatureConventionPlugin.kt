import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply{
                apply("snac.android.library")
                apply("snac.android.compose")
                apply("snac.android.hilt")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies{
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:model"))
                "implementation"(project(":core:common"))
                "implementation"(project(":core:data"))

                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())

                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.paging.compose").get())
            }
        }
    }

}