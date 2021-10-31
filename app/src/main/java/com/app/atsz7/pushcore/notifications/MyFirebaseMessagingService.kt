package com.app.atsz7.pushcore.notifications

import android.util.Log
import com.app.atsz7.pushcore.core.domain.entities.PushNotification
import com.app.atsz7.pushcore.core.notifications.PushCore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson

/**
 * The [MyFirebaseMessagingService] class is used as service to receive
 * the push notifications from Firebase.
 */

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MessagingService"
    }

    /**
     * The [pushCore] is a instance of [PushCore] created with
     * [PushCore] constructor using by lazy.
     */
    private val pushCore: PushCore by lazy {
        PushCore(this)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "Data: ${remoteMessage.data}")
        Log.d(TAG, "Notification: ${remoteMessage.notification}")
        Log.d(TAG, "Body: ${remoteMessage.notification?.body}")

        try {

            val json = Gson().toJson(remoteMessage.data)
            val notification = Gson().fromJson(json, PushNotification::class.java)
            pushCore.onReceive(notification)

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
    }
}
