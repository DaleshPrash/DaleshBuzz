// app/build.gradle.kts

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinparcelize)
    jacoco
}

android {
    namespace = "com.example.daleshbuzz"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.daleshbuzz"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // testInstrumentationRunner = "com.example.projectaoct22.HiltTestRunner"

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
        debug {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    // Merged multiple buildFeatures blocks into one
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }

    // Added composeOptions to specify the Kotlin Compiler Extension Version
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.8"
    }
}

jacoco {
    toolVersion = "0.8.10"
}

kapt {
    correctErrorTypes = true
}
ktlint {
    version.set("0.48.0")
    debug.set(false)
    android.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

dependencies {
    // --- UI Libraries ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.biometric)
    implementation(libs.androidx.cardview)
    implementation(libs.androidxviewpager2)

    // Jetpack Compose (using BOM)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // --- Navigation Libraries ---
    implementation(libs.androidxfragmentktx)
    implementation(libs.androidxnavigationfragmentktx)
    implementation(libs.androidxnavigationuiktx)

    // --- Networking Libraries ---
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging.interceptor)

    //--- Image Loading (Glide) ---
    implementation(libs.glide)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.biometric.ktx)
    kapt(libs.glide.compiler)

    // --- Lifecycle & State Management ---
    implementation(libs.androidxlifecycleviewmodelktx)
    implementation(libs.androidxlifecyclelivedataktx)

    // --- Coroutines & Async Programming ---
    implementation(libs.coroutinesandroid)
    implementation(libs.coroutinestesting)

    // --- Dependency Injection (Hilt) ---
    implementation(libs.hiltandroid)
    kapt(libs.hiltcompiler)

    // --- Database Libraries (Room) ---
    implementation(libs.roomruntime)
    kapt(libs.roomcompiler)
    implementation(libs.roomktx)

    implementation ("androidx.compose.material3:material3:1.2.1")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.5.0")
    implementation ("androidx.activity:activity-compose:1.7.2")


    implementation ("androidx.navigation:navigation-compose:2.7.7")


    // --- Testing Libraries (Unit, UI, and Mocking) ---
    testImplementation(libs.junit)
    testImplementation(libs.coroutinestesting)
    testImplementation(libs.mockk)
    testImplementation(libs.coretesting)



    // AndroidX testing core for Robolectric environment

    // Extended JUnit for AndroidX
    testImplementation ("androidx.test.ext:junit-ktx:1.1.5")

    // Robolectric for unit testing
    testImplementation ("org.robolectric:robolectric:4.10.3")

    // Optional: Truth assertions for better readability
    testImplementation ("com.google.truth:truth:1.0")
    testImplementation(libs.androidx.espressocore)


    // Android Test Libraries
    /* androidTestImplementation(libs.androidx.junit)
     androidTestImplementation(libs.androidx.espressocore)
     androidTestImplementation(platform(libs.androidx.compose.bom))
     androidTestImplementation(libs.androidx.uitestjunit4)
     androidTestImplementation(libs.mockkandroid)

     */
    androidTestImplementation(libs.mockkandroid)

    // Debug Implementations (for Fragment Testing and Compose Tooling)
    debugImplementation(libs.androidxfragmenttesting)
    debugImplementation(libs.androidx.uitooling)
    debugImplementation(libs.androidx.uitestmanifest)

    // testImplementation ("org.robolectric:robolectric:4.9")
    testImplementation ("org.robolectric:robolectric:4.13")
    testImplementation("androidx.test:core:1.5.0")
    implementation("io.coil-kt:coil-compose:2.5.0")

    //implementation("androidx.biometric:biometric:1.2.0")


}
