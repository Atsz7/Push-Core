package com.app.atsz7.pushcore.core.app

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * The [AppLifecycle] class is used to manage the life cycle of the application
 * and to know when it is in the foreground or the background.
 */

enum class AppLifecycle {

    INSTANCE;

    private var inForeground: Boolean = false
    fun isInForeground(): Boolean = inForeground

    object LifecycleCallbacks : Application.ActivityLifecycleCallbacks {

        override fun onActivityPaused(activity: Activity) {
            INSTANCE.inForeground = false
        }

        override fun onActivityResumed(activity: Activity) {
            INSTANCE.inForeground = true
        }

        override fun onActivityStarted(activity: Activity) {
            // Implementation not required
        }

        override fun onActivityDestroyed(activity: Activity) {
            // Implementation not required
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            // Implementation not required
        }

        override fun onActivityStopped(activity: Activity) {
            // Implementation not required
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            // Implementation not required
        }
    }
}
