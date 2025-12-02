# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

# For Android apps
-dontpreverify
-verbose

# Remove logging in production
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

# Keep Hilt classes
-keep class com.google.dagger.hilt.** { *; }
-keepclasseswithmembernames class * {
    @com.google.dagger.hilt.* <methods>;
}

# Keep Room classes
-keep class androidx.room.** { *; }
-keepclasseswithmembernames class * {
    @androidx.room.* <methods>;
}

# Keep Retrofit classes
-keep class retrofit2.** { *; }
-keepclasseswithmembernames interface * {
    @retrofit2.http.* <methods>;
}

# Keep OkHttp classes
-keep class okhttp3.** { *; }
-keepclasseswithmembernames class * {
    @okhttp3.* <methods>;
}

# Keep Gson classes
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }
-keepclasseswithmembernames class * {
    @com.google.gson.* <fields>;
}

# Keep model classes
-keep class com.nutricare.data.models.** { *; }
-keepclasseswithmembernames class com.nutricare.data.models.** {
    <init>(...);
    <fields>;
    <methods>;
}

# Keep DAO interfaces
-keep interface com.nutricare.data.local.** { *; }

# Keep API Service interfaces
-keep interface com.nutricare.data.remote.** { *; }

# Keep Repository classes
-keep class com.nutricare.data.repository.** { *; }

# Keep ViewModel classes
-keep class com.nutricare.viewmodel.** { *; }

# General Android rules
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
#-keep public class * extends androidx.fragment.app.Fragment

# Keep R classes
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep custom application classes
-keep class * extends android.app.Application
-keepclassmembers class * extends android.app.Application {
    void onCreate();
}

# Keep Activity and Fragment constructors
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
