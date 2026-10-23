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
        google()
        mavenCentral()
    }
}

rootProject.name = "Caloinescu_Tudor_ActivitateDAM2026"

// Aici legi direct folderele tale existente fără să muți nimic:
include(":Seminar01_1075:app")
include(":Seminar02_1075:app")
include(":Seminar03_1075:app")
include(":Seminar04_1075:app")

