package com.app.atsz7.pushcore.core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.atsz7.pushcore.core.R
import com.app.atsz7.pushcore.core.app.AppLifecycle
import com.app.atsz7.pushcore.core.data.notifications.PushSenderImpl
import com.app.atsz7.pushcore.core.domain.entities.PushNotification
import com.app.atsz7.pushcore.core.domain.notifications.PushSender

/**
 * The [PushCore] class is used to stored all domain logic to properly manage
 * the "push notifications" in the life cycle of the application.
 */

class PushCore(private val context: Context) {

    companion object {
        private var notificationId: Int = 1
        private const val TAG = "PushCore"
    }

    /**
     * The [PushSender] is a instance of [PushSender] created
     * with [PushSenderImpl] using by lazy.
     */
    private val pushSender: PushSender by lazy {
        PushSenderImpl()
    }

    private val defaultChannel: String by lazy {
        "${context.packageName}.default_channel"
    }

    /**
     * This method is used to handle a [notification] when
     * it is received.
     */
    fun onReceive(notification: PushNotification) {

        process(notification)

        if (AppLifecycle.INSTANCE.isInForeground()) {
            pushSender.send(notification)
            return
        }

        createDefaultNotificationChannel(context)
        showNotification(notification.subject, notification.message)
    }

    /**
     * This method is used to process the [notification] and
     * perform the corresponding actions according to the
     * type of notification.
     */
    private fun process(notification: PushNotification) {
        Log.d(TAG, "Processing notification: $notification")
    }

    private fun createDefaultNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name = context.getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val description = context.getString(R.string.channel_description)

            val channel = NotificationChannel(defaultChannel, name, importance).apply {
                this.description = description
            }

            val notifyManager: NotificationManager = context.getSystemService(
                    Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            notifyManager.createNotificationChannel(channel)
        }
    }

    /**
     * This method is used to create and display a notification
     * in the notification bar of the user's device with
     * an specific text [title] and [content].
     */
    private fun showNotification(title: String?, content: String?) {

        val builder = NotificationCompat.Builder(context, defaultChannel)
                .setContentTitle(title ?: "")
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setVibrate(longArrayOf(3000L))
                .setAutoCancel(true)

        builder.setContentText(content ?: "")
        NotificationManagerCompat.from(context).notify(notificationId++, builder.build())
    }
}
