//plugins {
//    alias(libs.plugins.android.application) apply false
//    //alias(libs.plugins.google.services) apply false
//    //alias(libs.plugins.firebase.crashlytics) apply false
//    alias(libs.plugins.kotlin.gradleplugin) apply false
//    alias(libs.plugins.dagger.hilt) apply false
//    alias(libs.plugins.compose.compiler) apply false
//    alias(libs.plugins.devtool.ks) apply false
//}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    //id("com.google.gms.google-services") version "4.4.4" apply false
    //id ("com.google.firebase.crashlytics")version "3.0.6" apply false
}

