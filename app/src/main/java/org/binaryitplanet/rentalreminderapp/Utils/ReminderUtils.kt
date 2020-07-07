package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class ReminderUtils(
    var day: Int,

    var month: Int,

    var year: Int
): Serializable {
}