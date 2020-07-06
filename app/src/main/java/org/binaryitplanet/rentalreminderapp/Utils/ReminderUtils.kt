package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = Config.TABLE_NAME_REMINDER)
data class ReminderUtils(
    @PrimaryKey
    @ColumnInfo(name = Config.COLUMN_ID)
    var id: Long,

    @ColumnInfo(name = Config.COLUMN_DAY)
    var date: Int,

    @ColumnInfo(name = Config.COLUMN_MONTH)
    var month: Int,

    @ColumnInfo(name = Config.COLUMN_YEAR)
    var year: Int
): Serializable {
}