package com.app.atsz7.pushcore.core.domain.notifications

import com.app.atsz7.pushcore.core.domain.entities.PushNotification

/**
 * The [PushSender] interface defines all methods that "PushSender implementation"
 * must implement to properly manage the sending "push notifications" in the life
 * cycle of the application.
 */

interface PushSender {

    /**
     * This method is used to send a [notification] to an
     * activity or fragment in the application and to
     * cache the [notification] for later sending.
     *
     * IMPORTANT: If the [notification] has already
     * been displayed, call the [remove] method to
     * avoid displaying it again.
     */
    fun send(notification: PushNotification)

    /**
     * This method is used to remove a [notification]
     * from the cache.
     */
    fun remove(notification: PushNotification)
}
