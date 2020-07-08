package org.binaryitplanet.rentalreminderapp.Services

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ReminderUtils
import java.util.*

class ReminderManager(
    val context: Context,
    val reminderUtils: ReminderUtils
) {
    private val TAG = "ReminderManager"

    private var REMINDER_REQUEST_CODE: Int = 0


    private lateinit var calendar: Calendar

    private lateinit var alarmManager: AlarmManager

    private lateinit var reminderPendingIntent: PendingIntent


    fun create() {

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager


        REMINDER_REQUEST_CODE = getRequestCode()

        calendar = Calendar.getInstance()
        calendar.set(
            reminderUtils.year,
            reminderUtils.month,
            reminderUtils.day,
            12,
            0,
            0
        )

        Log.d(TAG, "Start: ${calendar.timeInMillis}")

        setPendingIntent()

    }


    // Generating reminder request code
    private fun getRequestCode(): Int = reminderUtils.year * 10000 +
            reminderUtils.month * 100 + reminderUtils.day




    private fun setPendingIntent() {

        val reminderCode = REMINDER_REQUEST_CODE

        val reminderIntent = Intent(context, ReminderReceiver::class.java)
        val bundle = Bundle()
        bundle.putSerializable(Config.PENDING_REMINDER, reminderUtils)
        bundle.putString(Config.REMINDER_ID, reminderCode.toString())
        reminderIntent.putExtra(Config.NOTIFICATION_BUNDLE, bundle)

        reminderPendingIntent = PendingIntent.getBroadcast(
            context,
            REMINDER_REQUEST_CODE,
            reminderIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }


    fun setReminder(): Boolean {
        val currentTime = Calendar.getInstance().timeInMillis
        val reminderTime = calendar.timeInMillis

        if (reminderTime >= currentTime) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    reminderTime,
                    reminderPendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    reminderTime,
                    reminderPendingIntent
                )
            }
        }
        return true
    }

    fun cancelReminder(): Boolean {
        alarmManager.cancel(reminderPendingIntent)
        return true
    }
}