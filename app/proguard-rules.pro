# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/Android Studio.app/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-dontnote com.android.vending.licensing.ILicensingService

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    native <methods>;
}

#To maintain custom components names that are used on layouts XML:
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

#Maintain enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#Keep the R
-keepclassmembers class **.R$* {
    public static <fields>;
}

-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-dontwarn javax.**
-dontwarn io.realm.**
-keep class io.realm.**

-keep public class * {
    public protected *;
}
