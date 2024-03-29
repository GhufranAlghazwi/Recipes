package org.tuwaiq.recipes.util

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import org.tuwaiq.recipes.R
import org.tuwaiq.recipes.view.home.mainscreen.HomeActivity

object NotificationHelper {

    private const val CHANNEL_ID = "Recipes"

    private fun showNotification(context: Context, title: String?, body: String?){
        Intent(context, HomeActivity::class.java).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_MUTABLE)
            } else {
                PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_ONE_SHOT)
            }
            val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.heart_red)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            NotificationManagerCompat.from(context).notify(0, notification.build())
        }
    }

    fun sendNotificationToActivity(context: Context, title: String?, body: String?) {
        showNotification(context, title, body)

        val intent = Intent("recipes")
        intent.putExtra("title", title)
        intent.putExtra("body", body)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }
}