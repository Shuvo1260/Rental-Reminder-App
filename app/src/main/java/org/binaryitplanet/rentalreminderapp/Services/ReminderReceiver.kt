package org.binaryitplanet.rentalreminderapp.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ReminderUtils
import java.util.*

class ReminderReceiver: BroadcastReceiver() {

    private val TAG = "ReminderReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent!!.getBundleExtra(Config.NOTIFICATION_BUNDLE)

        Log.d(TAG, "ReminderReceived")

        if (!isRantClear(context!!, bundle)) {

            try {

                Log.d(TAG, "ReminderReceived Rant not received")
                // Building notification
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(
                        bundle?.getString(Config.REMINDER_ID),
                        Config.REMINDER_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                    )

                    val notificationManager =
                        context?.getSystemService(NotificationManager::class.java)
                    notificationManager?.createNotificationChannel(notificationChannel)
                }

                val notificationBuilder = NotificationCompat.Builder(
                    context!!,
                    bundle?.getString(Config.REMINDER_ID)!!
                )

                notificationBuilder
                    .setContentTitle(Config.REMINDER_TITLE)
                    .setContentText(Config.REMINDER_MESSAGE)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI).priority =
                    NotificationCompat.DEFAULT_ALL

                val notificationManagerCompat = NotificationManagerCompat.from(context)
                notificationManagerCompat.notify(
                    bundle?.getInt(Config.PENDING_REMINDER_CODE),
                    notificationBuilder.build()
                )
                setReminderNextDay(context, bundle?.get(Config.PENDING_REMINDER) as ReminderUtils)

            } catch (e: Exception) {
                Log.d(TAG, "NotificationError: ${e.message}")

            }
        } else {
            setReminderNextMonth(context)
        }
    }

    private fun setReminderNextDay(context: Context,
                                   reminderUtils: ReminderUtils) {
        val calendar = Calendar.getInstance()

        calendar.timeInMillis += (1440 * 60000)

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        val reminderUtils = ReminderUtils(day, month, year)
        val reminderManager = ReminderManager(context, reminderUtils)
        reminderManager.create()
        reminderManager.setReminder()
    }

    private fun setReminderNextMonth(context: Context) {
        val day = 7
        var month = Calendar.getInstance().get(Calendar.MONTH)
        var year = Calendar.getInstance().get(Calendar.YEAR)

        if (month == 11) {
            month = 0
            year++
        }
        else
            month++

        val reminderUtils = ReminderUtils(day, month, year)
        val reminderManager = ReminderManager(context, reminderUtils)
        reminderManager.create()
        reminderManager.setReminder()
    }

    private fun isRantClear(context: Context, bundle: Bundle?): Boolean {
        try {
//            val months = context.resources.getStringArray(R.array.months)
//            val reminderUtils = bundle?.get(Config.PENDING_REMINDER) as ReminderUtils
//            val month = months[Calendar.getInstance().get(Calendar.MONTH)]
//            val year = reminderUtils.year
//
//            val totalProperty = DatabaseManager.getInstance(context)
//                ?.getTenantDAO()?.countTotalPropertyByStatus(true)
//
//            val totalRantReceived = DatabaseManager.getInstance(context)
//                ?.getParticularDAO()?.countTotalParticularByStatus(month, year, "Credit")
//
//
//            Log.d(TAG, "ReminderReceived: $totalProperty $totalRantReceived")
//            if (totalProperty!! - totalRantReceived!! == 0)
//                return true
        }catch (e: Exception) {
            Log.d(TAG, "RantFailed: ${e.message}")
        }

        return false
    }
}