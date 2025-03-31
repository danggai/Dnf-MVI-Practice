plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.hiltPlugin)
}

android {
    namespace = "com.example.dnf_mvi_practice"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dnf_mvi_practice"
        minSdk = 29
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    // Hilt - DI
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // OkHttp3 & Retrofit - for network
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit)

    // Sandwich - Network success/fail Handling
    implementation(libs.sandwich)

    implementation(libs.orbit.core)
}