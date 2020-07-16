package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = Config.TABLE_NAME_OLD_TENANT)
data class OldTenantUtils(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Config.COLUMN_ID)
    val id: Long?,

    @ColumnInfo(name = Config.COLUMN_BUILDING_ID)
    val buildingId: Long?,

    @ColumnInfo(name = Config.COLUMN_TENANT_ID)
    val tenantId: Long?,

    @ColumnInfo(name = Config.COLUMN_BUILDING_NAME)
    val buildingName: String,

    @ColumnInfo(name = Config.COLUMN_LAST_ADDRESS)
    val address: String,

    @ColumnInfo(name = Config.COLUMN_TENANT_NAME)
    val tenantName: String,

    @ColumnInfo(name = Config.COLUMN_PHONE_NUMBER)
    val phoneNumber: String,

    @ColumnInfo(name = Config.COLUMN_JOIN_DATE)
    val joinDate: String,

    @ColumnInfo(name = Config.COLUMN_RENEW_DATE)
    val renewDate: String,

    @ColumnInfo(name = Config.COLUMN_LAST_RENT_RECEIVED)
    val lastRent: String,

    @ColumnInfo(name = Config.COLUMN_ID_PROOF)
    val idProof: String,

    @ColumnInfo(name = Config.COLUMN_TOTAL_DEBIT)
    var totalDebit: Int,

    @ColumnInfo(name = Config.COLUMN_TOTAL_CREDIT)
    var totalCredit: Int
): Serializable {
}