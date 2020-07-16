package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Comment
import java.io.Serializable

@Entity(tableName = Config.TABLE_NAME_TENANT)
data class TenantUtils(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Config.COLUMN_ID)
    val id: Long?,

    @ColumnInfo(name = Config.COLUMN_BUILDING_ID)
    var buildingId: Long?,

    @ColumnInfo(name = Config.COLUMN_TENANT_NAME)
    var tenantName: String,

    @ColumnInfo(name = Config.COLUMN_PHONE_NUMBER)
    var phoneNumber: String,

    @ColumnInfo(name = Config.COLUMN_JOIN_DATE)
    var joinDate: String,

    @ColumnInfo(name = Config.COLUMN_ID_PROOF)
    var idProof: String,

    @ColumnInfo(name = Config.COLUMN_TOTAL_DEBIT)
    var totalDebit: Int,

    @ColumnInfo(name = Config.COLUMN_TOTAL_CREDIT)
    var totalCredit: Int

) :Serializable {
}