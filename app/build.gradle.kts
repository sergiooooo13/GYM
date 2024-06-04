plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.beta.gym"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.beta.gym"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.ui.desktop)
    val nav_Version = "2.7.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_Version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_Version")

    implementation ("com.google.android.material:material:1.4.0")

    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    val room_version = "2.6.1" // Asegúrate de usar la versión más reciente

    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")

    // Añade la dependencia room-ktx
    implementation ("androidx.room:room-ktx:$room_version")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}