package org.binaryitplanet.rentalreminderapp.Utils

class Config {

    companion object {
//        Toolbar Title
        val TOOLBAR_TITLE_HOME = "Home"
        val TOOLBAR_TITLE_OLD_TENANT = "Old Tenant"
        val TOOLBAR_TITLE_BACKUP_AND_RESTORE = "Backup and Restore"
        val TOOLBAR_TITLE_ADD_PROPERTY = "Add property"
        val TOOLBAR_TITLE_EDIT_PROPERTY = "Edit property"
        val TOOLBAR_TITLE_ADD_PARTICULAR= "Add particular"
        val TOOLBAR_TITLE_ADD_TENANT= "Add tenant"
        val LIST_CACHED_SIZE = 1000
        val SHARED_PREFERENCE = "SharedPreference"
        val FIRST_REMINDER = "FirstReminder"
        val RENEW_DATE = "Renew date: "
        val NO_TENANT = "No tenant exist to leave"

        val SUCCESS_MESSAGE = "Success"
        val FAILED_MESSAGE  = "Failed"
        val LAST_RENT_EMPTY_MESSAGE = "No rent received yet"
        val LAST_RENT_MESSAGE = "Last rent received"
        val PREVIOUS_TENANT_TITLE = "Previous tenant name"
        val LEAVING_TENANT_TITLE = "Leaving tenant"
        val DELETE_TENANT_TITLE = "Delete tenant"
        val DELETE_PARTICULAR_TITLE = "Delete particular"
        val DELETE_PARTICULAR_MESSAGE = "Do you want to delete the particular?"
        val DELETE_PROPERTY_TITLE = "Delete property"
        val DELETE_PROPERTY_MESSAGE = "Do you want to delete the property?"
        val LEAVING_TENANT_MESSAGE = "Are you sure the tenant is leaving?"
        val DELETE_TENANT_MESSAGE = "Do you want to delete the tenant permanently?"
        val YES_MESSAGE = "Yes"
        val NO_MESSAGE = "No"
        val REQUEST_CALL = 103

        val PROPERTY_STATUS_OCCUPIED = "Occupied"
        val PROPERTY_STATUS_UNOCCUPIED = "Unoccupied"

//        Intent
        val PROPERTY_INFORMATION = "PropertyInformation"
        val PROPERTY_EDIT_FLAG = "PropertyEditFlag"
        val TENANT_INFORMATION = "TenantInformation"
//        Error
        val ERROR_INVALID_MESSAGE = "Inavlid value"

//        Reminder Section
        val REMINDER_CODE = "ReminderCode"
        val REMINDER_ID = "ReminderID"
        val REMINDER_NAME = "ReminderName"
        val PENDING_REMINDER_CODE = "PendingReminderCode"
        val PENDING_REMINDER = "PendingReminder"
        val REMINDER_TITLE = "You haven't received all of your rents. "
        val REMINDER_MESSAGE = "Please check your property list and see who hasn't given you rent."

//        Notification Section
        val NOTIFICATION_BUNDLE = "NotificationBundle"
        val NOTIFICATION_TITLE = "NotificationTitle"
        val NOTIFICATION_MESSAGE = "NotificationMessage"


//        Database
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Rental_Reminder"
        const val DATABASE_NAME_WITH_FORMAT = "Rental_Reminder.db"
        const val TABLE_NAME_TENANT = "Tenants"
        const val TABLE_NAME_OLD_TENANT = "Old_Tenants"
        const val TABLE_NAME_PROPERTY = "Property"
        const val TABLE_NAME_REMINDER = "Reminders"
        const val TABLE_NAME_PARTICULAS = "Particulars"

//        Table
        const val COLUMN_ID = "ID"
        const val COLUMN_BUILDING_ID = "Building_ID"
        const val COLUMN_TENANT_ID = "Tenant_ID"
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
        const val COLUMN_PAYMENT_TYPE = "Payment_type"
        const val COLUMN_TOTAL_DEBIT = "Total_debit"
        const val COLUMN_TOTAL_CREDIT = "Total_credit"
        const val COLUMN_LAST_RENT_RECEIVED = "Last_Rent"
        const val COLUMN_LAST_ADDRESS = "Address"
        const val COLUMN_RENEW_DATE = "Renew_Date"
        const val COLUMN_JOIN_DATE = "Join_Date"

//        Backup And Restore section
        const val SD_CARD_PATH = "/sdcard/"
        const val BACKUP_REQUEST_CODE = 0
        const val RESTORE_REQUEST_CODE = 1
    }
}