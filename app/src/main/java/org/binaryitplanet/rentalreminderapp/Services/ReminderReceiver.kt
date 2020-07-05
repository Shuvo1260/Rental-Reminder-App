package org.binaryitplanet.rentalreminderapp.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config

class ReminderReceiver: BroadcastReceiver() {

    private val TAG = "ReminderReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {
//        try {
//
//            // Rendering intent data
//            val dbManager = RoomDatabaseManager.getInstance(context!!)
//            val bundle = intent?.getBundleExtra(Config.NOTIFICATION_BUNDLE)
//            var reminderUtils = bundle?.getSerializable(Config.PENDING_REMINDER) as MedicineReminderUtils
//
//            reminderUtils = dbManager?.getMedicineReminderDAO()?.getMedicineReminder(reminderUtils.id)!!
//
//            Log.d(TAG, "Reminder Total: ${reminderUtils.totalReminder}")
//            Log.d(TAG, "Reminder number: ${bundle?.getInt(Config.REMINDER_CODE)}")
//
//            // Building notification
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val notificationChannel = NotificationChannel(
//                    bundle?.getString(Config.REMINDER_ID),
//                    Config.REMINDER_NAME,
//                    NotificationManager.IMPORTANCE_DEFAULT
//                )
//
//                val notificationManager = context?.getSystemService(NotificationManager::class.java)
//                notificationManager?.createNotificationChannel(notificationChannel)
//            }
//
//            val notificationBuilder = NotificationCompat.Builder(
//                context!!,
//                bundle?.getString(Config.REMINDER_ID)!!
//            )
//
//            notificationBuilder
//                .setContentTitle(bundle?.getString(Config.NOTIFICATION_TITLE))
//                .setContentText(bundle?.getString(Config.NOTIFICATION_MESSAGE))
//                .setAutoCancel(true)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI).priority = NotificationCompat.DEFAULT_ALL
//
//            val notificationManagerCompat = NotificationManagerCompat.from(context)
//            notificationManagerCompat.notify(
//                bundle?.getInt(Config.PENDING_REMINDER_CODE),
//                notificationBuilder.build()
//            )
//
//            /** Removing reminder from database
//             * if it is last reminder
//             */
//            if (reminderUtils.totalReminder == bundle?.getInt(Config.REMINDER_CODE)) {
//                dbManager.getMedicineReminderDAO().delete(reminderUtils)
//            }
//
//        }catch (e: Exception) {
//            Log.d(TAG, "NotificationError: ${e.message}")
//
//        }
    }
}