package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = Config.TABLE_NAME_PROPERTY)
data class OldTenantUtils(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Config.COLUMN_ID)
    val id: Long?,

    @ColumnInfo(name = Config.COLUMN_BUILDING_NAME)
    val buildingName: String,

    @ColumnInfo(name = Config.COLUMN_TENANT_NAME)
    val tenantName: String,

    @ColumnInfo(name = Config.COLUMN_PHONE_NUMBER)
    val phoneNumber: String
): Serializable {
}