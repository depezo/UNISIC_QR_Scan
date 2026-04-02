import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    //id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    //id("com.google.gms.google-services")
}

android {
    namespace = "cd.unisic.qrscan"
    compileSdk = 36

    buildFeatures {
        compose = true
        buildConfig = true
    }

    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
    }
    
    val baseUrl = properties.getProperty("baseUrl") ?: "https://lagrebe.com/api"

    defaultConfig {
        applicationId = "cd.unisic.qrscan"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val compose_version = "1.6.0"
    val hilt_version = "2.50"
    val ktor_version = "2.3.7"
    val camerax_version = "1.3.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp ("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Ktor
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // CameraX
   // implementation ("androidx.camera:camera-bom:1.3.0")
   // // Ajoutez les dépendances sans spécifier de version
   // implementation ("androidx.camera:camera-core")
   // implementation ("androidx.camera:camera-camera2")
   // implementation ("androidx.camera:camera-lifecycle")
   // implementation ("androidx.camera:camera-video")
   // implementation ("androidx.camera:camera-view")
   // implementation ("androidx.camera:camera-mlkit-vision") // La version correcte sera déduite


   implementation("androidx.camera:camera-core:$camerax_version")
   implementation("androidx.camera:camera-camera2:$camerax_version")
   implementation("androidx.camera:camera-lifecycle:$camerax_version")
   implementation("androidx.camera:camera-view:$camerax_version")
   implementation("androidx.camera:camera-mlkit-vision:1.4.2")

   implementation("com.google.mlkit:barcode-scanning:17.3.0")



    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    // bundled:
    implementation("io.github.g00fy2.quickie:quickie-bundled:1.11.0")
}
