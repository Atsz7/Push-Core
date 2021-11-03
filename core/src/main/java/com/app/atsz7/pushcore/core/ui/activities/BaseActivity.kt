package com.app.atsz7.pushcore.core.ui.activities

import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.app.atsz7.pushcore.core.data.notifications.PushSenderImpl
import com.app.atsz7.pushcore.core.domain.entities.PushNotification
import com.app.atsz7.pushcore.core.domain.notifications.PushSender
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * The [BaseActivity] class is the "base" activity in the application,
 * here the push notifications flow is implemented.
 */

open class BaseActivity : AppCompatActivity() {

    /**
     * The [PushSender] is a instance of [PushSender] created
     * with [PushSenderImpl] using by lazy.
     */
    protected val pushSender: PushSender by lazy {
        PushSenderImpl()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    open fun onPushNotification(notification: PushNotification) {

        Log.d(javaClass.name, "Push notification: $notification")

        pushSender.remove(notification)

        AlertDialog.Builder(this)
            .setTitle(notification.subject)
            .setMessage(notification.message)
            .setPositiveButton("Accept", null)
            .create()
            .show()
    }
}
