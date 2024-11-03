plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.ivanov23.testapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.ivanov23.testapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:adapter-delegate"))
    implementation(project(":features:search"))
    implementation(project(":features:favourites"))
    implementation(project(":features:responses"))
    implementation(project(":features:messages"))
    implementation(project(":features:profile"))

    implementation(libs.viewbinding)
    implementation(libs.dagger)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    ksp(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.material)
}