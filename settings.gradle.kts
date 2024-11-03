pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "TestApp"
include(":app")
include(":features:search")
include(":features:favourites")
include(":domain")
include(":core:ui-kit")
include(":data")
include(":core:adapter-delegate")
include(":features:messages")
include(":features:profile")
include(":features:responses")
