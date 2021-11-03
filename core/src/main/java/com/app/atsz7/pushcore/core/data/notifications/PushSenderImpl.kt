package com.app.atsz7.pushcore.core.data.notifications

import android.util.Log
import com.app.atsz7.pushcore.core.domain.entities.PushNotification
import com.app.atsz7.pushcore.core.domain.notifications.PushSender
import org.greenrobot.eventbus.EventBus

/**
 * The [PushSenderImpl] implements all methods defined by [PushSender]
 * to properly manage the sending "push notifications" in the life
 * cycle of the application.
 */

class PushSenderImpl : PushSender {

    /**
     * @see PushSender.send
     */
    override fun send(notification: PushNotification) {
        EventBus.getDefault().postSticky(notification)
    }

    /**
     * @see PushSender.remove
     */
    override fun remove(notification: PushNotification) {

        try {
            val stickyEvent = EventBus.getDefault().getStickyEvent(PushNotification::class.java)
            if (stickyEvent != null) {
                EventBus.getDefault().removeStickyEvent(notification)
            }
        } catch (ex: Exception) {
            Log.e(javaClass.name, "Push sender error: $ex")
        }
    }
}
