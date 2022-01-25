package org.tuwaiq.recipes.util

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.Constants
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseServiceReceiver : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

//        Log.d(TAG, "From: ${remoteMessage.from}")
//
//        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//        }
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null) {
            val body = remoteMessage.notification?.body
            val title = remoteMessage.notification?.title

            NotificationHelper.sendNotificationToActivity(this, title, body)
        }


    }


}