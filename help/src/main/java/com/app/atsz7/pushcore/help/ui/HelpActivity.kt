package com.app.atsz7.pushcore.help.ui

import android.os.Bundle
import android.widget.Toast
import com.app.atsz7.pushcore.core.domain.entities.PushNotification
import com.app.atsz7.pushcore.core.ui.activities.BaseActivity
import com.app.atsz7.pushcore.help.R

class HelpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
    }

    override fun onPushNotification(notification: PushNotification) {
        Toast.makeText(
            this, getString(R.string.help_push_notification), Toast.LENGTH_SHORT
        ).show()
    }
}
