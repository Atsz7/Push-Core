package com.app.atsz7.pushcore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.app.atsz7.pushcore.core.ui.activities.BaseActivity
import com.app.atsz7.pushcore.help.ui.HelpActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFirebaseToken()

        findViewById<Button>(R.id.btnAction).setOnClickListener {
            startActivity(Intent(this, HelpActivity::class.java))
        }
    }

    private fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->

            if (!task.isSuccessful) {
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.d(javaClass.name, "Firebase token: $token")
        })
    }
}
