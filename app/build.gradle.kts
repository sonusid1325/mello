plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt") // Required for Hilt's Kapt
}

hilt {
    enableAggregatingTask = false
}

android {
    namespace = "com.sonusid.mello"
    compileSdk = 36 // Android 14

    defaultConfig {
        applicationId = "com.sonusid.mello"
        minSdk = 29
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    @Suppress("UnstableApiUsage")
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // activity-compose: Used for ActivityResultLauncher, e.g., PickVisualMedia
    implementation(libs.androidx.activity.compose)

    // Compose BOM (Platform) - This should dictate many other Compose library versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    // Firebase (via BoM) - Good practice
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)

    // Hilt
    implementation(libs.hilt.android) // Keep only one instance
    implementation(libs.hilt.navigation.compose) // For Compose navigation integration with Hilt
    // Removed the duplicate implementation(libs.hilt.android)

    kapt(libs.hilt.compiler) // For Hilt annotation processing

    // Markdown for compose
    implementation(libs.compose.markdown)

    // Coil Compose (for image loading)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp) // If you need OkHttp integration for Coil

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Lottie animations
    implementation(libs.lottie.compose)
}