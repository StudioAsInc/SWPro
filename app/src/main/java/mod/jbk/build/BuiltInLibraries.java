package mod.jbk.build;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import a.a.a.Jp;
import a.a.a.KB;
import a.a.a.ProjectBuilder;
import a.a.a.oB;
import pro.sketchware.SketchApplication;

public class BuiltInLibraries {
    public static final File EXTRACTED_COMPILE_ASSETS_PATH = new File(SketchApplication.getContext().getFilesDir(), "libs");
    public static final File EXTRACTED_BUILT_IN_LIBRARIES_PATH = new File(EXTRACTED_COMPILE_ASSETS_PATH, "libs");
    public static final File EXTRACTED_BUILT_IN_LIBRARY_DEX_FILES_PATH = new File(EXTRACTED_COMPILE_ASSETS_PATH, "dexs");

    // None final so that field values won't be optimized into code, and to allow easy changing of library names due to that

    public static String ANDROIDX_ACTIVITY = "activity-1.10.1";
    public static String ANDROIDX_ANNOTATION = "annotation-1.5.1";
    public static String ANDROIDX_ANNOTATION_ANNOTATION_JVM = "annotation-jvm-1.9.1";
    public static String ANDROIDX_ANNOTATION_EXPERIMENTAL = "annotation-experimental-1.5.1";
    public static String ANDROIDX_APPCOMPAT = "appcompat-1.7.1";
    public static String ANDROIDX_APPCOMPAT_RESOURCES = "appcompat-resources-1.7.1";
    public static String ANDROIDX_ARCH_CORE_CORE_COMMON = "android-arch-core-common-2.2.0";
    public static String ANDROIDX_ASYNCLAYOUTINFLATER = "asynclayoutinflater-1.0.0";
    public static String ANDROIDX_BIOMETRIC_BIOMETRIC = "biometric-1.1.0";
    public static String ANDROIDX_BROWSER = "browser-1.9.0";
    public static String ANDROIDX_CARDVIEW = "cardview-1.0.0";
    public static String ANDROIDX_COLLECTION = "collection-1.5.0";
    public static String ANDROIDX_COLLECTION_JVM = "collection-jvm-1.5.0";
    public static String ANDROIDX_CONCURRENT_CONCURRENT_FUTURES_KTX = "concurrent-futures-ktx-1.3.0";
    public static String ANDROIDX_CONCURRENT_FUTURES = "concurrent-futures-1.3.0";
    public static String ANDROIDX_CONSTRAINTLAYOUT = "constraintlayout-2.1.2";
    public static String ANDROIDX_CONSTRAINTLAYOUT_CORE = "constraintlayout-core-1.0.2";
    public static String ANDROIDX_COORDINATORLAYOUT = "coordinatorlayout-1.1.0";
    public static String ANDROIDX_CORE = "core-1.17.0";
    public static String ANDROIDX_CORE_CORE_VIEWTREE = "core-viewtree-1.0.0";
    public static String ANDROIDX_CORE_KTX = "core-ktx-1.16.0";
    public static String ANDROIDX_CORE_RUNTIME = "core-runtime-2.1.0";
    public static String ANDROIDX_CREDENTIALS_CREDENTIALS = "credentials-1.5.0";
    public static String ANDROIDX_CREDENTIALS_CREDENTIALS_PLAY_SERVICES_AUTH = "credentials-play-services-auth-1.5.0";
    public static String ANDROIDX_CURSORADAPTER = "cursoradapter-1.0.0";
    public static String ANDROIDX_CUSTOMVIEW = "customview-1.1.0";
    public static String ANDROIDX_DOCUMENTFILE = "documentfile-1.0.1";
    public static String ANDROIDX_DRAWERLAYOUT = "drawerlayout-1.1.1";
    public static String ANDROIDX_DYNAMICANIMATION = "dynamicanimation-1.1.0";
    public static String ANDROIDX_EMOJI2 = "emoji2-1.0.1";
    public static String ANDROIDX_EMOJI2_VIEWS_HELPER = "emoji2-views-helper-1.0.1";
    public static String ANDROIDX_EXIFINTERFACE = "exifinterface-1.3.6";
    public static String ANDROIDX_FRAGMENT = "fragment-1.3.6";
    public static String ANDROIDX_GRAPHICS_SHAPES_ANDROID = "graphics-shapes-android-1.0.1";
    public static String ANDROIDX_INTERPOLATOR = "interpolator-1.0.0";
    public static String ANDROIDX_LEGACY_SUPPORT_CORE_UI = "legacy-support-core-ui-1.0.0";
    public static String ANDROIDX_LEGACY_SUPPORT_CORE_UTILS = "legacy-support-core-utils-1.0.0";
    public static String ANDROIDX_LEGACY_SUPPORT_V13 = "legacy-support-v13-1.0.0";
    public static String ANDROIDX_LEGACY_SUPPORT_V4 = "legacy-support-v4-1.0.0";
    public static String ANDROIDX_LIFECYCLE_COMMON = "lifecycle-common-2.6.2";
    public static String ANDROIDX_LIFECYCLE_LIVEDATA = "lifecycle-livedata-2.6.2";
    public static String ANDROIDX_LIFECYCLE_LIVEDATA_CORE = "lifecycle-livedata-core-2.6.2";
    public static String ANDROIDX_LIFECYCLE_PROCESS = "lifecycle-process-2.6.2";
    public static String ANDROIDX_LIFECYCLE_RUNTIME = "lifecycle-runtime-2.6.2";
    public static String ANDROIDX_LIFECYCLE_SERVICE = "lifecycle-service-2.6.2";
    public static String ANDROIDX_LIFECYCLE_VIEWMODEL = "lifecycle-viewmodel-2.6.2";
    public static String ANDROIDX_LIFECYCLE_VIEWMODEL_SAVEDSTATE = "lifecycle-viewmodel-savedstate-2.6.2";
    public static String ANDROIDX_LOADER = "loader-1.1.0";
    public static String ANDROIDX_LOCALBROADCASTMANAGER = "localbroadcastmanager-1.0.0";
    public static String ANDROIDX_MEDIA = "media-1.2.1";
    public static String ANDROIDX_MEDIA3_MEDIA3_COMMON = "media3-common-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_CONTAINER = "media3-container-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_DATABASE = "media3-database-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_DATASOURCE = "media3-datasource-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_DECODER = "media3-decoder-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_EXOPLAYER = "media3-exoplayer-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_EXOPLAYER_HLS = "media3-exoplayer-hls-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_EXTRACTOR = "media3-extractor-1.8.0";
    public static String ANDROIDX_MEDIA3_MEDIA3_UI = "media3-ui-1.8.0";
    public static String ANDROIDX_MULTIDEX = "multidex-2.0.1";
    public static String ANDROIDX_PRINT = "print-1.0.0";
    public static String ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES = "ads-adservices-1.1.0-beta12";
    public static String ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES_JAVA = "ads-adservices-java-1.1.0-beta12";
    public static String ANDROIDX_PROFILEINSTALLER = "profileinstaller-1.4.1";
    public static String ANDROIDX_RECYCLERVIEW = "recyclerview-1.2.1";
    public static String ANDROIDX_RESOURCEINSPECTION_ANNOTATION = "resourceinspection-annotation-1.0.1";
    public static String ANDROIDX_ROOM_COMMON = "room-common-2.6.1";
    public static String ANDROIDX_ROOM_ROOM_KTX = "room-ktx-2.6.1";
    public static String ANDROIDX_ROOM_RUNTIME = "room-runtime-2.6.1";
    public static String ANDROIDX_SAVEDSTATE = "savedstate-1.2.1";
    public static String ANDROIDX_SLIDINGPANELAYOUT = "slidingpanelayout-1.1.0";
    public static String ANDROIDX_SQLITE = "sqlite-2.4.0";
    public static String ANDROIDX_SQLITE_FRAMEWORK = "sqlite-framework-2.4.0";
    public static String ANDROIDX_STARTUP_RUNTIME = "startup-runtime-1.2.0";
    public static String ANDROIDX_SWIPEREFRESHLAYOUT = "swiperefreshlayout-1.2.0-alpha01";
    public static String ANDROIDX_TRACING = "tracing-1.2.0";
    public static String ANDROIDX_TRACING_TRACING_KTX = "tracing-ktx-1.2.0";
    public static String ANDROIDX_TRANSITION = "transition-1.6.0";
    public static String ANDROIDX_VECTORDRAWABLE = "vectordrawable-1.2.0";
    public static String ANDROIDX_VECTORDRAWABLE_ANIMATED = "vectordrawable-animated-1.2.0";
    public static String ANDROIDX_VERSIONEDPARCELABLE = "versionedparcelable-1.1.1";
    public static String ANDROIDX_VIEWPAGER = "viewpager-1.0.0";
    public static String ANDROIDX_VIEWPAGER2 = "viewpager2-1.0.0";
    public static String ANDROIDX_WORK_RUNTIME = "work-runtime-2.10.3";
    public static String CIRCLEIMAGEVIEW = "circleimageview-3.1.0";
    public static String CODEVIEW = "CodeView-0.4.0";
    public static String COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT = "play-services-measurement-23.0.0";
    public static String COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT_API = "play-services-measurement-api-23.0.0";
    public static String COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT_IMPL = "play-services-measurement-impl-23.0.0";
    public static String COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_BASE = "play-services-base-18.7.2";
    public static String COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_STATS = "play-services-stats-17.1.0";
    public static String COM_GOOGLE_ANDROID_LIBRARIES_INDENTITY_GOOGLEID_GOOGLEID = "googleid-1.1.1";
    public static String COM_GOOGLE_ERRORPRONE_ERROR_PRONE_ANNOTATIONS = "error_prone_annotations-2.41.0";
    public static String COM_GOOGLE_FIREBASE_FIREBASE_ANNOTATIONS = "firebase-annotations-16.2.0";
    public static String COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS = "firebase-installations-17.0.1";
    public static String COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS_INTEROP = "firebase-installations-interop-17.2.0";
    public static String DEV_RIKKA_SHIZUKU_AIDL = "shizuku-aidl-12.2.0";
    public static String DEV_RIKKA_SHIZUKU_API = "shizuku-api-12.2.0";
    public static String DEV_RIKKA_SHIZUKU_PROVIDER = "shizuku-provider-12.2.0";
    public static String DEV_RIKKA_SHIZUKU_SHARED = "shizuku-shared-12.2.0";
    public static String FACEBOOK_ADS_AUDIENCE_NETWORK_SDK = "audience-network-sdk-5.9.0";
    public static String FIREBASE_AUTH = "firebase-auth-19.0.0";
    public static String FIREBASE_AUTH_INTEROP = "firebase-auth-interop-18.0.0";
    public static String FIREBASE_COMMON = "firebase-common-20.1.0";
    public static String FIREBASE_COMPONENTS = "firebase-components-18.0.0";
    public static String FIREBASE_DATABASE = "firebase-database-19.3.1";
    public static String FIREBASE_DATABASE_COLLECTION = "firebase-database-collection-17.0.0";
    public static String FIREBASE_DYNAMIC_LINKS = "firebase-dynamic-links-19.0.0";
    public static String FIREBASE_IID = "firebase-iid-19.0.0";
    public static String FIREBASE_IID_INTEROP = "firebase-iid-interop-17.0.0";
    public static String FIREBASE_MEASUREMENT_CONNECTOR = "firebase-measurement-connector-19.0.0";
    public static String FIREBASE_MESSAGING = "firebase-messaging-19.0.0";
    public static String FIREBASE_STORAGE = "firebase-storage-19.0.0";
    public static String GLIDE = "glide-4.16.0";
    public static String GLIDE_ANNOTATIONS = "annotations-4.16.0";
    public static String GLIDE_COMPILER = "compiler-4.16.0";
    public static String GLIDE_DISKLRUCACHE = "disklrucache-4.16.0";
    public static String GLIDE_GIFDECODER = "gifdecoder-4.16.0";
    public static String GOOGLE_AUTO_VALUE_ANNOTATIONS = "auto-value-annotations-1.6.5";
    public static String GSON = "gson-2.8.7";
    public static String GUAVA = "guava-33.1.0-android";
    public static String GUAVA_LISTENABLEFUTURE = "listenablefuture-1.0.0";
    public static String HTTP_LEGACY_ANDROID = "http-legacy-android-28";
    public static String JAKARTA_INJECT_JAKARTA_INJECT_API = "firebase-annotations-16.2.0";
    public static String JETBRAINS_ANNOTATIONS = "org-jetbrains-annotations-23.0.0";
    public static String JUNIT_JUNIT = "junit-4.13.2";
    public static String JETBRAINS_KOTLIN_STDLIB = "kotlin-stdlib-2.0.21";
    public static String KOTLIN_STDLIB_JDK7 = "kotlin-stdlib-jdk7-1.6.21";
    public static String LOTTIE = "lottie-3.4.0";
    public static String MATERIAL = "material-1.14.0-alpha04";
    public static String OKHTTP = "okhttp-3.9.1";
    public static String OKIO = "okio-1.17.4";
    public static String ONESIGNAL = "OneSignal-3.14.0";
    public static String ORG_HAMCREAST_HAMCREAST_CORE = "hamcrest-core-1.3";
    public static String ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_ANDROID = "kotlinx-coroutines-android-1.7.3";
    public static String ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_ANDROID = "kotlinx-coroutines-core-android-1.6.4";
    public static String ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_JVM = "kotlinx-coroutines-core-jvm-1.7.3";
    public static String ORG_JETBRAINS_KOTLIN_KOTLIN_STDLIB_JDK8 = "kotlin-stdlib-jdk8-1.6.21";
    public static String ORG_JSPECIFY_JSPECIFY = "jspecify-1.0.0";
    public static String OTPVIEW = "OTPView-0.1.0";
    public static String PATTERN_LOCK_VIEW = "pattern-lock-view";
    public static String PLAY_SERVICES_ADS = "play-services-ads-22.2.0";
    public static String PLAY_SERVICES_ADS_BASE = "play-services-ads-base-22.2.0";
    public static String PLAY_SERVICES_ADS_IDENTIFIER = "play-services-ads-identifier-18.2.0";
    public static String PLAY_SERVICES_ADS_LITE = "play-services-ads-lite-22.2.0";
    public static String PLAY_SERVICES_APPSET = "play-services-appset-16.0.2";
    public static String PLAY_SERVICES_AUTH = "play-services-auth-19.0.0";
    public static String PLAY_SERVICES_AUTH_API_PHONE = "play-services-auth-api-phone-17.0.5";
    public static String PLAY_SERVICES_AUTH_BASE = "play-services-auth-base-17.1.2";
    public static String PLAY_SERVICES_BASE = "play-services-base-18.2.0";
    public static String PLAY_SERVICES_BASEMENT = "play-services-basement-18.7.1";
    public static String PLAY_SERVICES_GASS = "play-services-gass-20.0.0";
    public static String PLAY_SERVICES_GCM = "play-services-gcm-17.0.0";
    public static String PLAY_SERVICES_IID = "play-services-iid-17.0.0";
    public static String PLAY_SERVICES_LOCATION = "play-services-location-18.0.0";
    public static String PLAY_SERVICES_MAPS = "play-services-maps-17.0.1";
    public static String PLAY_SERVICES_MEASUREMENT_BASE = "play-services-measurement-base-23.0.0";
    public static String PLAY_SERVICES_MEASUREMENT_SDK_API = "play-services-measurement-sdk-api-23.0.0";
    public static String PLAY_SERVICES_PLACES_PLACEREPORT = "play-services-places-placereport-17.0.0";
    public static String PLAY_SERVICES_STATS = "play-services-stats-17.0.0";
    public static String PLAY_SERVICES_TASKS = "play-services-tasks-18.3.2";
    public static String USER_MESSAGING_PLATFORM = "user-messaging-platform-2.0.0";
    public static String WAVE_SIDE_BAR = "wave-side-bar";
    public static String ANDROID_YOUTUBE_PLAYER = "android-youtube-player-10.0.5";


    public static final BuiltInLibrary[] KNOWN_BUILT_IN_LIBRARIES = {

            //==========Guava==========
            new BuiltInLibrary(GUAVA, List.of(COM_GOOGLE_ERRORPRONE_ERROR_PRONE_ANNOTATIONS)),
            new BuiltInLibrary(GUAVA_LISTENABLEFUTURE),
            //==========Guava==========

            //==========Kotlin==========
            new BuiltInLibrary(JETBRAINS_ANNOTATIONS),
            new BuiltInLibrary(JETBRAINS_KOTLIN_STDLIB, List.of(JETBRAINS_ANNOTATIONS)),
            new BuiltInLibrary(KOTLIN_STDLIB_JDK7, List.of(JETBRAINS_KOTLIN_STDLIB)),

            new BuiltInLibrary(ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_JVM),
            new BuiltInLibrary(ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_ANDROID),
            new BuiltInLibrary(ORG_JETBRAINS_KOTLIN_KOTLIN_STDLIB_JDK8),
            new BuiltInLibrary(ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_ANDROID),
            //==========Kotlin==========

            //==========AndroidX==========
            //Core & Annotation
            new BuiltInLibrary(ANDROIDX_COLLECTION, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_COLLECTION_JVM),

            new BuiltInLibrary(ANDROIDX_CORE, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ANNOTATION_EXPERIMENTAL, ANDROIDX_COLLECTION,
                    ANDROIDX_CONCURRENT_FUTURES, ANDROIDX_LIFECYCLE_RUNTIME, ANDROIDX_VERSIONEDPARCELABLE, ANDROIDX_ARCH_CORE_CORE_COMMON,
                    ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_JVM, ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_CORE_ANDROID, ORG_JETBRAINS_KOTLIN_KOTLIN_STDLIB_JDK8), "androidx.core"),
            new BuiltInLibrary(ANDROIDX_CORE_KTX),
            new BuiltInLibrary(ANDROIDX_CORE_CORE_VIEWTREE),
            new BuiltInLibrary(ANDROIDX_ARCH_CORE_CORE_COMMON, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_CORE_RUNTIME, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ARCH_CORE_CORE_COMMON)),

            new BuiltInLibrary(ANDROIDX_CONCURRENT_FUTURES, List.of(ANDROIDX_ANNOTATION, GUAVA_LISTENABLEFUTURE)),
            new BuiltInLibrary(ANDROIDX_CONCURRENT_CONCURRENT_FUTURES_KTX),

            new BuiltInLibrary(ANDROIDX_PROFILEINSTALLER, List.of(ANDROIDX_STARTUP_RUNTIME, ANDROIDX_ANNOTATION, ANDROIDX_CONCURRENT_FUTURES)),

            new BuiltInLibrary(ANDROIDX_TRACING, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_TRACING_TRACING_KTX, List.of(), "androidx.tracing.ktx"),

            new BuiltInLibrary(ANDROIDX_VERSIONEDPARCELABLE, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION)),


            //UI Components & Layout
            new BuiltInLibrary(ANDROIDX_ACTIVITY, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_LIFECYCLE_RUNTIME,
                    ANDROIDX_LIFECYCLE_VIEWMODEL, ANDROIDX_LIFECYCLE_VIEWMODEL_SAVEDSTATE, ANDROIDX_SAVEDSTATE, ANDROIDX_TRACING,
                    ANDROIDX_ANNOTATION_ANNOTATION_JVM, ANDROIDX_CORE_CORE_VIEWTREE, ANDROIDX_LIFECYCLE_COMMON, ANDROIDX_SAVEDSTATE, GUAVA),
                    "androidx.activity"),

            new BuiltInLibrary(ANDROIDX_ASYNCLAYOUTINFLATER, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE)),

            new BuiltInLibrary(ANDROIDX_APPCOMPAT, List.of(ANDROIDX_ACTIVITY, ANDROIDX_ANNOTATION, ANDROIDX_APPCOMPAT_RESOURCES, ANDROIDX_COLLECTION,
                    ANDROIDX_CORE, ANDROIDX_CURSORADAPTER, ANDROIDX_DRAWERLAYOUT, ANDROIDX_EMOJI2, ANDROIDX_EMOJI2_VIEWS_HELPER, ANDROIDX_FRAGMENT,
                    ANDROIDX_LIFECYCLE_RUNTIME, ANDROIDX_LIFECYCLE_VIEWMODEL, ANDROIDX_RESOURCEINSPECTION_ANNOTATION, ANDROIDX_SAVEDSTATE),
                    "androidx.appcompat"),
            new BuiltInLibrary(ANDROIDX_APPCOMPAT_RESOURCES, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_VECTORDRAWABLE,
                    ANDROIDX_VECTORDRAWABLE_ANIMATED), "androidx.appcompat.resources"),

            new BuiltInLibrary(ANDROIDX_BROWSER, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CONCURRENT_FUTURES, ANDROIDX_CORE, ANDROIDX_INTERPOLATOR,
                    GUAVA_LISTENABLEFUTURE), "androidx.browser"),

            new BuiltInLibrary(ANDROIDX_CARDVIEW, List.of(ANDROIDX_ANNOTATION), "androidx.cardview"),

            new BuiltInLibrary(ANDROIDX_CONSTRAINTLAYOUT, List.of(ANDROIDX_APPCOMPAT, ANDROIDX_CORE, ANDROIDX_CONSTRAINTLAYOUT_CORE, ANDROIDX_PROFILEINSTALLER),
                    "androidx.constraintlayout.widget"),
            new BuiltInLibrary(ANDROIDX_CONSTRAINTLAYOUT_CORE),

            new BuiltInLibrary(ANDROIDX_COORDINATORLAYOUT, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_CUSTOMVIEW),
                    "androidx.coordinatorlayout"),

            new BuiltInLibrary(ANDROIDX_CURSORADAPTER, List.of(ANDROIDX_ANNOTATION)),

            new BuiltInLibrary(ANDROIDX_CUSTOMVIEW, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE)),

            new BuiltInLibrary(ANDROIDX_DRAWERLAYOUT, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_CUSTOMVIEW),
                    "androidx.drawerlayout"),

            new BuiltInLibrary(ANDROIDX_DYNAMICANIMATION, List.of(ANDROIDX_CORE, ORG_JSPECIFY_JSPECIFY,
                    ANDROIDX_COLLECTION), "androidx.dynamicanimation"),

            new BuiltInLibrary(ANDROIDX_FRAGMENT, List.of(ANDROIDX_ACTIVITY, ANDROIDX_ANNOTATION, ANDROIDX_ANNOTATION_EXPERIMENTAL,
                    ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_LIFECYCLE_LIVEDATA_CORE, ANDROIDX_LIFECYCLE_VIEWMODEL,
                    ANDROIDX_LIFECYCLE_VIEWMODEL_SAVEDSTATE, ANDROIDX_LOADER, ANDROIDX_SAVEDSTATE, ANDROIDX_VIEWPAGER), "androidx.fragment"),

            new BuiltInLibrary(ANDROIDX_INTERPOLATOR, List.of(ANDROIDX_ANNOTATION)),

            new BuiltInLibrary(ANDROIDX_RECYCLERVIEW, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_LEGACY_SUPPORT_CORE_UI),
                    "androidx.recyclerview"),

            new BuiltInLibrary(ANDROIDX_SLIDINGPANELAYOUT, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_CUSTOMVIEW)),

            new BuiltInLibrary(ANDROIDX_SWIPEREFRESHLAYOUT, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_INTERPOLATOR),
                    "androidx.swiperefreshlayout"),

            new BuiltInLibrary(ANDROIDX_TRANSITION, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ORG_JSPECIFY_JSPECIFY,
                    ANDROIDX_COLLECTION, ANDROIDX_DYNAMICANIMATION), "androidx.transition"),
            new BuiltInLibrary(ANDROIDX_VECTORDRAWABLE, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CORE)),
            new BuiltInLibrary(ANDROIDX_VECTORDRAWABLE_ANIMATED, List.of(ANDROIDX_COLLECTION, ANDROIDX_INTERPOLATOR, ANDROIDX_VECTORDRAWABLE)),

            new BuiltInLibrary(ANDROIDX_VIEWPAGER, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_CUSTOMVIEW)),
            new BuiltInLibrary(ANDROIDX_VIEWPAGER2, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_FRAGMENT,
                    ANDROIDX_RECYCLERVIEW), "androidx.viewpager2"),


            //Emoji & Graphics
            new BuiltInLibrary(ANDROIDX_EMOJI2, List.of(ANDROIDX_ANNOTATION, ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_LIFECYCLE_PROCESS,
                    ANDROIDX_STARTUP_RUNTIME)),
            new BuiltInLibrary(ANDROIDX_EMOJI2_VIEWS_HELPER, List.of(ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_EMOJI2)),

            new BuiltInLibrary(ANDROIDX_GRAPHICS_SHAPES_ANDROID),


            //Legacy Support
            new BuiltInLibrary(ANDROIDX_LEGACY_SUPPORT_CORE_UI, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ASYNCLAYOUTINFLATER, ANDROIDX_CONSTRAINTLAYOUT,
                    ANDROIDX_COORDINATORLAYOUT, ANDROIDX_CORE, ANDROIDX_CURSORADAPTER, ANDROIDX_CUSTOMVIEW, ANDROIDX_DRAWERLAYOUT, ANDROIDX_INTERPOLATOR,
                    ANDROIDX_LEGACY_SUPPORT_CORE_UTILS, ANDROIDX_SLIDINGPANELAYOUT, ANDROIDX_SWIPEREFRESHLAYOUT, ANDROIDX_VIEWPAGER)),
            new BuiltInLibrary(ANDROIDX_LEGACY_SUPPORT_CORE_UTILS, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_DOCUMENTFILE, ANDROIDX_LOADER,
                    ANDROIDX_LOCALBROADCASTMANAGER, ANDROIDX_PRINT)),

            new BuiltInLibrary(ANDROIDX_LEGACY_SUPPORT_V13, List.of(ANDROIDX_LEGACY_SUPPORT_V4)),
            new BuiltInLibrary(ANDROIDX_LEGACY_SUPPORT_V4, List.of(ANDROIDX_CORE, ANDROIDX_FRAGMENT, ANDROIDX_LEGACY_SUPPORT_CORE_UI,
                    ANDROIDX_LEGACY_SUPPORT_CORE_UTILS, ANDROIDX_MEDIA)),


            //Lifecycle & Saved State
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_COMMON, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_LIVEDATA, List.of(ANDROIDX_ARCH_CORE_CORE_COMMON, ANDROIDX_CORE_RUNTIME, ANDROIDX_LIFECYCLE_LIVEDATA_CORE)),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_LIVEDATA_CORE, List.of(ANDROIDX_LIFECYCLE_COMMON, ANDROIDX_ARCH_CORE_CORE_COMMON, ANDROIDX_CORE_RUNTIME)),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_PROCESS, List.of(ANDROIDX_LIFECYCLE_RUNTIME, ANDROIDX_STARTUP_RUNTIME)),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_RUNTIME, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ARCH_CORE_CORE_COMMON, ANDROIDX_CORE_RUNTIME,
                    ANDROIDX_LIFECYCLE_COMMON), "androidx.lifecycle.runtime"),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_SERVICE, List.of(ANDROIDX_LIFECYCLE_RUNTIME)),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_VIEWMODEL, List.of(ANDROIDX_ANNOTATION), "androidx.lifecycle.viewmodel"),
            new BuiltInLibrary(ANDROIDX_LIFECYCLE_VIEWMODEL_SAVEDSTATE, List.of(ANDROIDX_ANNOTATION, ANDROIDX_SAVEDSTATE,
                    ANDROIDX_LIFECYCLE_LIVEDATA_CORE, ANDROIDX_LIFECYCLE_VIEWMODEL)),

            new BuiltInLibrary(ANDROIDX_SAVEDSTATE, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ARCH_CORE_CORE_COMMON, ANDROIDX_LIFECYCLE_COMMON),
                    "androidx.savedstate"),


            //Data & Persistence
            new BuiltInLibrary(ANDROIDX_DOCUMENTFILE, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_MULTIDEX),
            new BuiltInLibrary(ANDROIDX_PRINT, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_RESOURCEINSPECTION_ANNOTATION, List.of(ANDROIDX_ANNOTATION)),

            new BuiltInLibrary(ANDROIDX_ROOM_COMMON, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_ROOM_RUNTIME, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE_RUNTIME, ANDROIDX_ROOM_COMMON,
                    ANDROIDX_SQLITE_FRAMEWORK, ANDROIDX_SQLITE)),
            new BuiltInLibrary(ANDROIDX_ROOM_ROOM_KTX, List.of(), "androidx.room.ktx"),

            new BuiltInLibrary(ANDROIDX_STARTUP_RUNTIME, List.of(ANDROIDX_ANNOTATION, ANDROIDX_TRACING), "androidx.startup"),
            new BuiltInLibrary(ANDROIDX_WORK_RUNTIME, List.of(ANDROIDX_ANNOTATION_EXPERIMENTAL, ANDROIDX_CORE, ANDROIDX_LIFECYCLE_LIVEDATA,
                    ANDROIDX_LIFECYCLE_SERVICE, ANDROIDX_ROOM_RUNTIME, ANDROIDX_SQLITE, ANDROIDX_SQLITE_FRAMEWORK, ANDROIDX_STARTUP_RUNTIME,
                    GUAVA_LISTENABLEFUTURE, ORG_JETBRAINS_KOTLINX_KOTLINX_COROUTINES_ANDROID, JETBRAINS_ANNOTATIONS, ORG_JSPECIFY_JSPECIFY,
                    ANDROIDX_CONCURRENT_CONCURRENT_FUTURES_KTX, ANDROIDX_ROOM_ROOM_KTX, ANDROIDX_TRACING_TRACING_KTX), "androidx.work"),


            //SQL
            new BuiltInLibrary(ANDROIDX_SQLITE, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_SQLITE_FRAMEWORK, List.of(ANDROIDX_ANNOTATION, ANDROIDX_SQLITE)),

            //Media
            new BuiltInLibrary(ANDROIDX_MEDIA, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_VERSIONEDPARCELABLE), "androidx.media"),

            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_COMMON, List.of(ANDROIDX_ANNOTATION_EXPERIMENTAL, GUAVA, ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_CONTAINER, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_DATASOURCE, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_MEDIA3_MEDIA3_DATABASE, ANDROIDX_ANNOTATION, ANDROIDX_EXIFINTERFACE)),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_DECODER, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_EXTRACTOR, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_MEDIA3_MEDIA3_CONTAINER, ANDROIDX_ANNOTATION, ANDROIDX_MEDIA3_MEDIA3_DECODER)),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_DATABASE, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_ANNOTATION)),

            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_EXOPLAYER, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_MEDIA3_MEDIA3_DATASOURCE,
                    ANDROIDX_MEDIA3_MEDIA3_CONTAINER, ANDROIDX_MEDIA3_MEDIA3_DATABASE, ANDROIDX_MEDIA3_MEDIA3_DECODER,
                    ANDROIDX_MEDIA3_MEDIA3_EXTRACTOR), "androidx.media3.exoplayer"),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_UI, List.of(ANDROIDX_MEDIA3_MEDIA3_COMMON, ANDROIDX_ANNOTATION, ANDROIDX_RECYCLERVIEW), "androidx.media3.ui"),
            new BuiltInLibrary(ANDROIDX_MEDIA3_MEDIA3_EXOPLAYER_HLS, List.of(ANDROIDX_MEDIA3_MEDIA3_EXOPLAYER, ANDROIDX_ANNOTATION)),


            //APIs
            new BuiltInLibrary(ANDROIDX_EXIFINTERFACE, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(ANDROIDX_LOADER, List.of(ANDROIDX_ANNOTATION, ANDROIDX_CORE, ANDROIDX_LIFECYCLE_LIVEDATA, ANDROIDX_LIFECYCLE_VIEWMODEL)),
            new BuiltInLibrary(ANDROIDX_LOCALBROADCASTMANAGER, List.of(ANDROIDX_ANNOTATION)),


            //Credentials & security
            new BuiltInLibrary(ANDROIDX_CREDENTIALS_CREDENTIALS, List.of(ANDROIDX_BIOMETRIC_BIOMETRIC, ANDROIDX_CREDENTIALS_CREDENTIALS_PLAY_SERVICES_AUTH,
                    COM_GOOGLE_ANDROID_LIBRARIES_INDENTITY_GOOGLEID_GOOGLEID), "androidx.credentials"),
            new BuiltInLibrary(ANDROIDX_BIOMETRIC_BIOMETRIC, List.of(), "androidx.biometric"),
            new BuiltInLibrary(ANDROIDX_CREDENTIALS_CREDENTIALS_PLAY_SERVICES_AUTH, List.of(), "androidx.credentials.play.services.auth"),
            new BuiltInLibrary(COM_GOOGLE_ANDROID_LIBRARIES_INDENTITY_GOOGLEID_GOOGLEID),


            //Privacy Sandbox
            new BuiltInLibrary(ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES, List.of(), "androidx.privacysandbox.ads.adservices"),
            new BuiltInLibrary(ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES_JAVA, List.of(), "androidx.privacysandbox.ads.adservices.java"),
            //==========AndroidX==========

            //==========Material==========
            new BuiltInLibrary(MATERIAL, List.of(ANDROIDX_ANNOTATION, ANDROIDX_ANNOTATION_EXPERIMENTAL, ANDROIDX_APPCOMPAT, ANDROIDX_CARDVIEW,
                    ANDROIDX_CONSTRAINTLAYOUT, ANDROIDX_COORDINATORLAYOUT, ANDROIDX_CORE, ANDROIDX_DRAWERLAYOUT, ANDROIDX_DYNAMICANIMATION,
                    ANDROIDX_FRAGMENT, ANDROIDX_LIFECYCLE_RUNTIME, ANDROIDX_RECYCLERVIEW, ANDROIDX_TRANSITION, ANDROIDX_VECTORDRAWABLE,
                    ANDROIDX_VIEWPAGER2, JETBRAINS_KOTLIN_STDLIB, ANDROIDX_COLLECTION_JVM, ANDROIDX_GRAPHICS_SHAPES_ANDROID,
                    COM_GOOGLE_ERRORPRONE_ERROR_PRONE_ANNOTATIONS), "com.google.android.material"),
            //==========Material==========

            //==========Google Firebase==========
            //Core & Common
            new BuiltInLibrary(FIREBASE_COMMON, List.of(FIREBASE_COMPONENTS, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),
            new BuiltInLibrary(FIREBASE_COMPONENTS, List.of(COM_GOOGLE_FIREBASE_FIREBASE_ANNOTATIONS, ANDROIDX_ANNOTATION, COM_GOOGLE_ERRORPRONE_ERROR_PRONE_ANNOTATIONS)),
            new BuiltInLibrary(COM_GOOGLE_FIREBASE_FIREBASE_ANNOTATIONS, List.of(JAKARTA_INJECT_JAKARTA_INJECT_API)),


            //Auth
            new BuiltInLibrary(FIREBASE_AUTH, List.of(FIREBASE_AUTH_INTEROP, FIREBASE_COMMON, ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_FRAGMENT,
                    ANDROIDX_LOCALBROADCASTMANAGER, PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),

            new BuiltInLibrary(FIREBASE_AUTH_INTEROP, List.of(FIREBASE_COMMON, PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),



            //Database
            new BuiltInLibrary(FIREBASE_DATABASE, List.of(FIREBASE_AUTH_INTEROP, FIREBASE_COMMON, FIREBASE_COMPONENTS, FIREBASE_DATABASE_COLLECTION, ANDROIDX_ANNOTATION,
                    PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),

            new BuiltInLibrary(FIREBASE_DATABASE_COLLECTION),


            //Dynamic Link
            new BuiltInLibrary(FIREBASE_DYNAMIC_LINKS, List.of(FIREBASE_COMMON, FIREBASE_MEASUREMENT_CONNECTOR, PLAY_SERVICES_BASE,
                    PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),


            //IID
            new BuiltInLibrary(FIREBASE_IID, List.of(FIREBASE_COMMON, FIREBASE_IID_INTEROP, ANDROIDX_COLLECTION, ANDROIDX_CORE,
                    ANDROIDX_LEGACY_SUPPORT_CORE_UTILS, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_STATS, PLAY_SERVICES_TASKS)),

            new BuiltInLibrary(FIREBASE_IID_INTEROP, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT)),


            //Measurement
            new BuiltInLibrary(FIREBASE_MEASUREMENT_CONNECTOR, List.of(PLAY_SERVICES_BASEMENT, COM_GOOGLE_FIREBASE_FIREBASE_ANNOTATIONS)),


            //Messaging
            new BuiltInLibrary(FIREBASE_MESSAGING, List.of(FIREBASE_COMMON, FIREBASE_IID, FIREBASE_MEASUREMENT_CONNECTOR, ANDROIDX_COLLECTION,
                    ANDROIDX_CORE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS), "com.google.firebase.messaging"),


            //Storage
            new BuiltInLibrary(FIREBASE_STORAGE, List.of(FIREBASE_AUTH_INTEROP, FIREBASE_COMMON, ANDROIDX_ANNOTATION, PLAY_SERVICES_BASE,
                    PLAY_SERVICES_TASKS)),


            //Installations
            new BuiltInLibrary(COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS, List.of(PLAY_SERVICES_TASKS, FIREBASE_COMMON, FIREBASE_COMPONENTS, COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS_INTEROP)),
            new BuiltInLibrary(COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS_INTEROP, List.of(PLAY_SERVICES_TASKS, ANDROIDX_ANNOTATION)),
            //==========Google Firebase==========

            //==========Play Services==========
            //Ads
            new BuiltInLibrary(PLAY_SERVICES_ADS, List.of(PLAY_SERVICES_ADS_BASE, PLAY_SERVICES_ADS_IDENTIFIER,
                    PLAY_SERVICES_ADS_LITE, PLAY_SERVICES_APPSET, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS,
                    ANDROIDX_BROWSER, ANDROIDX_COLLECTION, ANDROIDX_CORE),
                    "com.google.android.gms.ads.impl"),

            new BuiltInLibrary(PLAY_SERVICES_ADS_BASE, List.of(PLAY_SERVICES_BASEMENT)),
            new BuiltInLibrary(PLAY_SERVICES_ADS_IDENTIFIER, List.of(PLAY_SERVICES_BASEMENT)),
            new BuiltInLibrary(PLAY_SERVICES_ADS_LITE, List.of(PLAY_SERVICES_ADS_BASE, PLAY_SERVICES_BASEMENT,
                    PLAY_SERVICES_MEASUREMENT_SDK_API, ANDROIDX_WORK_RUNTIME, USER_MESSAGING_PLATFORM), "com.google.android.gms.ads"),

            new BuiltInLibrary(PLAY_SERVICES_GASS, List.of(PLAY_SERVICES_ADS_BASE, PLAY_SERVICES_BASEMENT)),

            new BuiltInLibrary(USER_MESSAGING_PLATFORM, List.of(ANDROIDX_ANNOTATION, PLAY_SERVICES_ADS_IDENTIFIER,
                    PLAY_SERVICES_BASEMENT)), //Dialog consent


            //Auth & Account
            new BuiltInLibrary(PLAY_SERVICES_AUTH, List.of(PLAY_SERVICES_AUTH_API_PHONE, PLAY_SERVICES_AUTH_BASE, PLAY_SERVICES_BASE,
                    PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS, ANDROIDX_FRAGMENT, ANDROIDX_LOADER), "com.google.android.gms.auth.api"),

            new BuiltInLibrary(PLAY_SERVICES_AUTH_API_PHONE),
            new BuiltInLibrary(PLAY_SERVICES_AUTH_BASE),


            //Core & Common
            new BuiltInLibrary(PLAY_SERVICES_BASE, List.of(PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS, ANDROIDX_COLLECTION, ANDROIDX_CORE,
                    ANDROIDX_FRAGMENT), "com.google.android.gms.base"),
            new BuiltInLibrary(PLAY_SERVICES_BASEMENT, List.of(ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_FRAGMENT),
                    "com.google.android.gms.common"),

            new BuiltInLibrary(PLAY_SERVICES_STATS, List.of(PLAY_SERVICES_BASEMENT, ANDROIDX_LEGACY_SUPPORT_CORE_UTILS)),
            new BuiltInLibrary(PLAY_SERVICES_TASKS, List.of(PLAY_SERVICES_BASEMENT)),

            new BuiltInLibrary(PLAY_SERVICES_APPSET, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS)),


            //Location & Map
            new BuiltInLibrary(PLAY_SERVICES_LOCATION, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_PLACES_PLACEREPORT,
                    PLAY_SERVICES_TASKS)),

            new BuiltInLibrary(PLAY_SERVICES_MAPS, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, ANDROIDX_FRAGMENT),
                    "com.google.android.gms.maps"),

            new BuiltInLibrary(PLAY_SERVICES_PLACES_PLACEREPORT, List.of(PLAY_SERVICES_BASEMENT)),


            //Messaging & Cloud
            new BuiltInLibrary(PLAY_SERVICES_GCM, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_IID, PLAY_SERVICES_STATS,
                    ANDROIDX_COLLECTION, ANDROIDX_CORE, ANDROIDX_LEGACY_SUPPORT_CORE_UTILS), "com.google.android.gms.gcm"),

            new BuiltInLibrary(PLAY_SERVICES_IID, List.of(PLAY_SERVICES_BASE, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_STATS, PLAY_SERVICES_TASKS,
                    ANDROIDX_COLLECTION, ANDROIDX_CORE)),


            //Measurement & Analytics
            new BuiltInLibrary(PLAY_SERVICES_MEASUREMENT_BASE, List.of(PLAY_SERVICES_BASEMENT)),
            new BuiltInLibrary(PLAY_SERVICES_MEASUREMENT_SDK_API, List.of(PLAY_SERVICES_BASEMENT, PLAY_SERVICES_MEASUREMENT_BASE)),

            new BuiltInLibrary(COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT, List.of(PLAY_SERVICES_MEASUREMENT_BASE, COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT_IMPL,
                    PLAY_SERVICES_MEASUREMENT_SDK_API, PLAY_SERVICES_ADS_IDENTIFIER, COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_BASE,
                    PLAY_SERVICES_BASEMENT, PLAY_SERVICES_TASKS, COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_STATS,
                    ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES, ANDROIDX_PRIVACYSANDBOX_ADS_ADS_ADSERVICES_JAVA)),
            new BuiltInLibrary(COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT_IMPL),
            new BuiltInLibrary(COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_BASE, List.of(), "com.google.android.gms.base"),
            new BuiltInLibrary(COM_GOOGLE_ANDROID_GMS_PLAY_SERVIVES_STATS),

            new BuiltInLibrary(COM_GOOGLE_ANDROID_GMS_PLAY_SERVICES_MEASUREMENT_API, List.of(PLAY_SERVICES_ADS_IDENTIFIER, PLAY_SERVICES_BASEMENT, PLAY_SERVICES_MEASUREMENT_BASE,
                    PLAY_SERVICES_MEASUREMENT_SDK_API, PLAY_SERVICES_TASKS, FIREBASE_COMMON, FIREBASE_COMPONENTS, COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS,
                    COM_GOOGLE_FIREBASE_FIREBASE_INSTALLATIONS_INTEROP, FIREBASE_MEASUREMENT_CONNECTOR, GUAVA, JETBRAINS_KOTLIN_STDLIB)),
            //==========Play Services==========

            //==========Onesignal==========
            new BuiltInLibrary(ONESIGNAL, List.of(ANDROIDX_BROWSER, ANDROIDX_CARDVIEW, ANDROIDX_FRAGMENT, ANDROIDX_MEDIA, FIREBASE_MESSAGING,
                    PLAY_SERVICES_ADS_IDENTIFIER, PLAY_SERVICES_BASE, PLAY_SERVICES_LOCATION), "com.onesignal"),
            //==========Onesignal==========

            //==========Facebook ADS==========
            new BuiltInLibrary(FACEBOOK_ADS_AUDIENCE_NETWORK_SDK),
            //==========Facebook ADS==========

            //==========Glide==========
            new BuiltInLibrary(GLIDE, List.of(GLIDE_ANNOTATIONS, GLIDE_DISKLRUCACHE, GLIDE_GIFDECODER, ANDROIDX_EXIFINTERFACE, ANDROIDX_FRAGMENT,
                    ANDROIDX_VECTORDRAWABLE_ANIMATED, GLIDE_COMPILER), "com.bumptech.glide"),

            new BuiltInLibrary(GLIDE_ANNOTATIONS),
            new BuiltInLibrary(GLIDE_DISKLRUCACHE),
            new BuiltInLibrary(GLIDE_GIFDECODER, List.of(ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(GLIDE_COMPILER),
            //==========Glide==========

            //==========Lottie==========
            new BuiltInLibrary(LOTTIE, List.of(ANDROIDX_APPCOMPAT, OKIO), "com.airbnb.lottie"),
            //==========Lottie==========

            //==========Okhttp==========
            new BuiltInLibrary(OKHTTP, List.of(OKIO)),
            new BuiltInLibrary(OKIO),
            //==========Okhttp==========

            //==========Gson==========
            new BuiltInLibrary(GSON),
            //==========Gson==========

            //==========Circle ImageView==========
            new BuiltInLibrary(CIRCLEIMAGEVIEW, List.of(ANDROIDX_ANNOTATION), "de.hdodenhof.circleimageview"),
            //==========Circle ImageView==========

            //==========Code View==========
            new BuiltInLibrary(CODEVIEW, List.of(), "br.tiagohm.codeview"),
            //==========Code View==========

            //==========Wake side bar==========
            new BuiltInLibrary(WAVE_SIDE_BAR, List.of(), "com.sayuti.lib"),
            //==========Wake side bar==========

            //==========YouTube Player==========
            new BuiltInLibrary(ANDROID_YOUTUBE_PLAYER, List.of(ANDROIDX_APPCOMPAT, ANDROIDX_RECYCLERVIEW, KOTLIN_STDLIB_JDK7),
                    "com.pierfrancescosoffritti.androidyoutubeplayer"),
            //==========YouTube Player==========

            //==========OTPView==========
            new BuiltInLibrary(OTPVIEW, List.of(ANDROIDX_APPCOMPAT, ANDROIDX_CORE_KTX, ANDROIDX_CONSTRAINTLAYOUT, KOTLIN_STDLIB_JDK7),
                    "affan.ahmad.otp"),
            //==========OTPView==========

            //==========Patten Lock View==========
            new BuiltInLibrary(PATTERN_LOCK_VIEW, List.of(ANDROIDX_CORE, JETBRAINS_ANNOTATIONS), "com.andrognito.patternlockview"),
            //==========Patten Lock View==========

            //==========Shizuku==========
            new BuiltInLibrary(DEV_RIKKA_SHIZUKU_PROVIDER, List.of(ANDROIDX_ANNOTATION, DEV_RIKKA_SHIZUKU_API)),
            new BuiltInLibrary(DEV_RIKKA_SHIZUKU_API, List.of(DEV_RIKKA_SHIZUKU_AIDL, DEV_RIKKA_SHIZUKU_SHARED, ANDROIDX_ANNOTATION)),
            new BuiltInLibrary(DEV_RIKKA_SHIZUKU_AIDL, List.of()),
            new BuiltInLibrary(DEV_RIKKA_SHIZUKU_SHARED, List.of(DEV_RIKKA_SHIZUKU_AIDL, ANDROIDX_ANNOTATION)),
            //==========Shizuku==========

            //==========Http Legacy==========
            //Used to maintain compatibility for older apps or libraries that still use Apache HTTP Client on Android 6.0+
            new BuiltInLibrary(HTTP_LEGACY_ANDROID),
            //==========Http Legacy==========

            //==========Annotations==========
            new BuiltInLibrary(ANDROIDX_ANNOTATION), //Annotations @Nullable, @NonNull,...
            new BuiltInLibrary(ANDROIDX_ANNOTATION_ANNOTATION_JVM), //Annotations @Nullable, @NonNull,...
            new BuiltInLibrary(ANDROIDX_ANNOTATION_EXPERIMENTAL, List.of(JETBRAINS_KOTLIN_STDLIB)), //Annotations @Experimental, @UseExperimental,...
            new BuiltInLibrary(GOOGLE_AUTO_VALUE_ANNOTATIONS), //Automatically generate equals(), toString(),... code
            new BuiltInLibrary(JAKARTA_INJECT_JAKARTA_INJECT_API), //Annotations @Inject, @Qualifier,...
            //==========Annotations==========

            //==========Test==========
            new BuiltInLibrary(ORG_JSPECIFY_JSPECIFY),
            new BuiltInLibrary(COM_GOOGLE_ERRORPRONE_ERROR_PRONE_ANNOTATIONS, List.of(JUNIT_JUNIT)),
            new BuiltInLibrary(JUNIT_JUNIT, List.of(ORG_HAMCREAST_HAMCREAST_CORE)),
            new BuiltInLibrary(ORG_HAMCREAST_HAMCREAST_CORE),
            //==========Test==========
    };

    public static File getLibraryPath(String libraryName) {
        return new File(EXTRACTED_BUILT_IN_LIBRARIES_PATH, libraryName);
    }

    public static String getLibraryPathString(String libraryName) {
        return getLibraryPath(libraryName).getAbsolutePath();
    }

    public static File getLibraryClassesJarPath(String libraryName) {
        return new File(getLibraryPath(libraryName), "classes.jar");
    }

    public static String getLibraryClassesJarPathString(String libraryName) {
        return getLibraryClassesJarPath(libraryName).getAbsolutePath();
    }

    public static File getLibraryDexFile(String libraryName) {
        return new File(EXTRACTED_BUILT_IN_LIBRARY_DEX_FILES_PATH, libraryName + ".dex");
    }

    public static String getLibraryDexFilePath(String libraryName) {
        return new File(EXTRACTED_BUILT_IN_LIBRARY_DEX_FILES_PATH, libraryName + ".dex").getAbsolutePath();
    }

    /**
     * @throws IllegalArgumentException Thrown if the specified library doesn't have any assets.
     */
    public static File getLibraryAssets(String libraryName) {
        Jp library = new Jp(libraryName);

        if (library.hasAssets()) {
            return new File(EXTRACTED_BUILT_IN_LIBRARIES_PATH, libraryName + File.separator + "assets");
        } else {
            throw new IllegalArgumentException("Built-in library '" + libraryName + "' doesn't have any assets.");
        }
    }

    /**
     * @throws IllegalArgumentException Thrown if the specified library doesn't have any assets.
     */
    public static String getLibraryAssetsPath(String libraryName) {
        return getLibraryAssets(libraryName).getAbsolutePath();
    }

    public static File getLibraryResources(String libraryName) {
        Jp library = new Jp(libraryName);

        if (library.hasResources()) {
            return new File(EXTRACTED_BUILT_IN_LIBRARIES_PATH, libraryName + File.separator + "res");
        } else {
            throw new IllegalArgumentException("Built-in library '" + libraryName + "' doesn't have any resources.");
        }
    }

    public static String getLibraryResourcesPath(String libraryName) {
        return getLibraryResources(libraryName).getAbsolutePath();
    }

    public static File getLibraryProguardConfiguration(String libraryName) {
        return new File(EXTRACTED_BUILT_IN_LIBRARIES_PATH, libraryName + File.separator + "proguard.txt");
    }

    public static String getLibraryProguardConfigurationPath(String libraryName) {
        return getLibraryProguardConfiguration(libraryName).getAbsolutePath();
    }

    public static void extractCompileAssets(@NonNull BuildProgressReceiver... progressReceivers) {
        if (!EXTRACTED_COMPILE_ASSETS_PATH.exists()) {
            if (!EXTRACTED_COMPILE_ASSETS_PATH.mkdirs()) {
                throw new RuntimeException(new IOException("Failed to create directory " + EXTRACTED_COMPILE_ASSETS_PATH));
            }
        }

        String dexsArchiveName = "dexs.zip";
        String libsArchiveName = "libs.zip";
        String testkeyArchiveName = "testkey.zip";

        String dexsArchivePath = new File(BuiltInLibraries.EXTRACTED_COMPILE_ASSETS_PATH, dexsArchiveName).getAbsolutePath();
        String libsArchivePath = new File(BuiltInLibraries.EXTRACTED_COMPILE_ASSETS_PATH, libsArchiveName).getAbsolutePath();
        String testkeyArchivePath = new File(BuiltInLibraries.EXTRACTED_COMPILE_ASSETS_PATH, testkeyArchiveName).getAbsolutePath();
        String dexsDirectoryPath = BuiltInLibraries.EXTRACTED_BUILT_IN_LIBRARY_DEX_FILES_PATH.getAbsolutePath();
        String libsDirectoryPath = BuiltInLibraries.EXTRACTED_BUILT_IN_LIBRARIES_PATH.getAbsolutePath();
        String testkeyDirectoryPath = new File(BuiltInLibraries.EXTRACTED_COMPILE_ASSETS_PATH, "testkey").getAbsolutePath();

        String baseAssetsPath = "libs" + File.separator;
        oB fileUtil = new oB(false);

        maybeExtractAndroidJar(progressReceivers);

        if (ProjectBuilder.hasFileChanged(baseAssetsPath + dexsArchiveName, dexsArchivePath)) {
            for (BuildProgressReceiver receiver : progressReceivers) {
                receiver.onProgress("Extracting built-in libraries' DEX files...", 4);
            }
            /* Delete the directory */
            fileUtil.b(dexsDirectoryPath);
            /* Create the directories */
            fileUtil.f(dexsDirectoryPath);
            /* Extract dexs.zip to dexs/ */
            new KB().a(dexsArchivePath, dexsDirectoryPath);
        }
        if (ProjectBuilder.hasFileChanged(baseAssetsPath + libsArchiveName, libsArchivePath)) {
            for (BuildProgressReceiver receiver : progressReceivers) {
                receiver.onProgress("Extracting built-in libraries' resources...", 5);
            }
            /* Delete the directory */
            fileUtil.b(libsDirectoryPath);
            /* Create the directories */
            fileUtil.f(libsDirectoryPath);
            /* Extract libs.zip to libs/ */
            new KB().a(libsArchivePath, libsDirectoryPath);
        }
        maybeExtractCoreLambdaStubsJar();
        if (ProjectBuilder.hasFileChanged(baseAssetsPath + testkeyArchiveName, testkeyArchivePath)) {
            for (BuildProgressReceiver receiver : progressReceivers) {
                receiver.onProgress("Extracting built-in signing keys...", 6);
            }
            /* Delete the directory */
            fileUtil.b(testkeyDirectoryPath);
            /* Create the directories */
            fileUtil.f(testkeyDirectoryPath);
            /* Extract testkey.zip to testkey/ */
            new KB().a(testkeyArchivePath, testkeyDirectoryPath);
        }
    }

    public static void maybeExtractAndroidJar(@NonNull BuildProgressReceiver... receivers) {
        String androidJarArchiveName = "android.jar.zip";
        String androidJarPath = new File(EXTRACTED_COMPILE_ASSETS_PATH, androidJarArchiveName).getAbsolutePath();
        if (ProjectBuilder.hasFileChanged("libs" + File.separator + androidJarArchiveName, androidJarPath)) {
            for (BuildProgressReceiver receiver : receivers) {
                receiver.onProgress("Extracting built-in android.jar...", 7);
            }
            /* Delete android.jar */
            new oB().c(EXTRACTED_COMPILE_ASSETS_PATH.getAbsolutePath() + File.separator + "android.jar");
            /* Extract android.jar.zip to android.jar */
            new KB().a(androidJarPath, EXTRACTED_COMPILE_ASSETS_PATH.getAbsolutePath());
        }
    }

    public static void maybeExtractCoreLambdaStubsJar() {
        String coreLambdaStubsJarName = "core-lambda-stubs.jar";
        String coreLambdaStubsJarPath = new File(BuiltInLibraries.EXTRACTED_COMPILE_ASSETS_PATH, coreLambdaStubsJarName).getAbsolutePath();
        ProjectBuilder.hasFileChanged("libs" + File.separator + coreLambdaStubsJarName, coreLambdaStubsJarPath);
    }

    public static class BuiltInLibrary implements Parcelable {
        public static final Creator<BuiltInLibrary> CREATOR = new Creator<>() {
            @Override
            public BuiltInLibrary createFromParcel(Parcel in) {
                return new BuiltInLibrary(in);
            }

            @Override
            public BuiltInLibrary[] newArray(int size) {
                return new BuiltInLibrary[size];
            }
        };
        private final String name;
        private final List<String> dependencyNames;
        private final String packageName;
        private final boolean hasResources;

        /**
         * @param packageName Can be <code>null</code> for no resources, though then
         *                    {@link #BuiltInLibrary(String, List)} is advised.
         */
        public BuiltInLibrary(String name, List<String> dependencyNames, String packageName) {
            this.name = name;
            this.dependencyNames = dependencyNames;
            this.packageName = packageName;
            hasResources = packageName != null;
        }

        /**
         * Constructs a {@link BuiltInLibrary} with specified dependencies but no resources.
         */
        public BuiltInLibrary(String name, List<String> dependencyNames) {
            this(name, dependencyNames, null);
        }

        /**
         * Constructs a {@link BuiltInLibrary} with no dependencies and resources.
         */
        public BuiltInLibrary(String name) {
            this(name, List.of());
        }

        protected BuiltInLibrary(Parcel in) {
            name = in.readString();
            dependencyNames = in.createStringArrayList();
            packageName = in.readString();
            hasResources = in.readInt() != 0;
        }

        public static Optional<BuiltInLibrary> ofName(String name) {
            for (BuiltInLibrary builtInLibrary : KNOWN_BUILT_IN_LIBRARIES) {
                if (builtInLibrary.getName().equals(name)) {
                    return Optional.of(builtInLibrary);
                }
            }

            return Optional.empty();
        }

        public String getName() {
            return name;
        }

        public List<String> getDependencyNames() {
            return dependencyNames;
        }

        public Optional<String> getPackageName() {
            return packageName == null ? Optional.empty() : Optional.of(packageName);
        }

        public boolean hasResources() {
            return hasResources;
        }

        @Override
        @NonNull
        public String toString() {
            return "BuiltInLibrary{" +
                    "name='" + name + '\'' +
                    ", dependencyNames=" + dependencyNames +
                    ", packageName='" + packageName + '\'' +
                    ", hasResources=" + hasResources +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeStringList(dependencyNames);
            dest.writeString(packageName);
            dest.writeInt(hasResources ? 1 : 0);
        }
    }
}
