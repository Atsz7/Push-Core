package com.app.atsz7.pushcore.core.domain.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * The [PushNotification] class is used to parse a push notification
 * to pass it through the application.
 */

@Parcelize
data class PushNotification(
    @SerializedName("subject")
    var subject: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("type")
    var type: Int
) : Parcelable {

    companion object {

        /**
         * The [KEY] constant is used as "key" to pass
         * the [PushNotification] object to an
         * activity in the intent.
         */
        const val KEY = "PUSH_NOTIFICATION"
    }
}
