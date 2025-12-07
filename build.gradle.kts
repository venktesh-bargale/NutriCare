plugins {
    id("com.android.application") version "8.13.1" apply false
    id("com.android.library")     version "8.13.1" apply false

    // Kotlin (move to 2.2.x)
    id("org.jetbrains.kotlin.android") version "2.2.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.21" apply false

    // KSP — must start with the same Kotlin prefix (2.2.x-...)
    id("com.google.devtools.ksp") version "2.2.10-2.0.2" apply false

    id("com.google.dagger.hilt.android") version "2.48" apply false
}
