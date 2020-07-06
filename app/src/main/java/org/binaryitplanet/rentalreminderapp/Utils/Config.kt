package org.binaryitplanet.rentalreminderapp.Utils

class Config {

    companion object {
//        Toolbar Title
        val TOOLBAR_TITLE_HOME = "Home"
        val TOOLBAR_TITLE_OLD_TENANT = "Old Tenant"
        val TOOLBAR_TITLE_BACKUP_AND_RESTORE = "Backup and Restore"
        val TOOLBAR_TITLE_ADD_PROPERTY = "Add property"
        val TOOLBAR_TITLE_ADD_PARTICULAR= "Add particular"
        val LIST_CACHED_SIZE = 1000

        val SUCCESS_MESSAGE = "Success"
        val FAILED_MESSAGE  = "Failed"
        val LAST_RANT_EMPTY_MESSAGE = "No rant received yet"
        val LAST_RANT_MESSAGE = "Last rant received"
        val PREVIOUS_TENANT_TITLE = "Previous tenant name"
        val LEAVING_TENANT_TITLE = "Leaving tenant"
        val LEAVING_TENANT_MESSAGE = "Are you sure the tenant is leaving?"
        val YES_MESSAGE = "Yes"
        val NO_MESSAGE = "No"

        val PROPERTY_STATUS_OCCUPIED = "Occupied"
        val PROPERTY_STATUS_UNOCCUPIED = "Unoccupied"

//        Intent
        val PROPERTY_INFORMATION = "PropertyInformation"
//        Error
        val ERROR_INVALID_MESSAGE = "Inavlid value"

//        Reminder Section
        val REMINDER_CODE = "ReminderCode"
        val REMINDER_ID = "ReminderID"
        val REMINDER_NAME = "ReminderName"
        val PENDING_REMINDER_CODE = "PendingReminderCode"
        val PENDING_REMINDER = "PendingReminder"

//        Notification Section
        val NOTIFICATION_BUNDLE = "NotificationBundle"
        val NOTIFICATION_TITLE = "NotificationTitle"
        val NOTIFICATION_MESSAGE = "NotificationMessage"


//        Database
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Rental_Reminder"
        const val DATABASE_NAME_WITH_FORMAT = "Rental_Reminder.db"
        const val TABLE_NAME_TENANT = "Tenants"
        const val TABLE_NAME_REMINDER = "Reminders"
        const val TABLE_NAME_PARTICULAS = "Particulars"

//        Table
        const val COLUMN_ID = "ID"
        const val COLUMN_USER_ID = "User_ID"
        const val COLUMN_DAY = "Day"
        const val COLUMN_MONTH = "Month"
        const val COLUMN_YEAR = "Year"
        const val COLUMN_DATE = "Date"
        const val COLUMN_AMOUNT = "Amount"
        const val COLUMN_REMARK = "Remark"
        const val COLUMN_COMMENT = "Comment"
        const val COLUMN_BUILDING_NAME = "Building_Name"
        const val COLUMN_TENANT_NAME = "Tenant_Name"
        const val COLUMN_PHONE_NUMBER = "Phone_number"
        const val COLUMN_ID_PROOF = "Id_Proof"
        const val COLUMN_PROPERTY_STATUS = "Property_Status"
        const val COLUMN_TRANSACTION_TYPE = "Transaction_type"
        const val COLUMN_TOTAL_DEBIT = "Total_debit"
        const val COLUMN_TOTAL_CREDIT = "Total_credit"
        const val COLUMN_LAST_RANT_RECEIVED = "Last_Rant"

//        Backup And Restore section
        const val SD_CARD_PATH = "/sdcard/"
        const val BACKUP_REQUEST_CODE = 0
        const val RESTORE_REQUEST_CODE = 1
    }
}