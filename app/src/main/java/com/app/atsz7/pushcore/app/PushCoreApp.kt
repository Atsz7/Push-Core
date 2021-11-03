package com.app.atsz7.pushcore.app

import android.app.Application
import com.app.atsz7.pushcore.core.app.AppLifecycle

class PushCoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Creates life cycle owner to listen changes when
         * app goes to foreground or background.
         */
        registerActivityLifecycleCallbacks(AppLifecycle.LifecycleCallbacks)
    }
}
