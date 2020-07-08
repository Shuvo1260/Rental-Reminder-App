package org.binaryitplanet.rentalreminderapp.Utils

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = Config.TABLE_NAME_PARTICULAS)
data class ParticularUtils(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Config.COLUMN_ID)
    val Id: Long?,

    @ColumnInfo(name = Config.COLUMN_TENANT_ID)
    val tenantID: Long,

    @ColumnInfo(name = Config.COLUMN_TRANSACTION_TYPE)
    val transactionType: String,

    @ColumnInfo(name = Config.COLUMN_PAYMENT_TYPE)
    val paymentType: String,

    @ColumnInfo(name = Config.COLUMN_DATE)
    val date: String,

    @ColumnInfo(name = Config.COLUMN_MONTH)
    val month: String,

    @ColumnInfo(name = Config.COLUMN_YEAR)
    val year: Int,

    @ColumnInfo(name = Config.COLUMN_AMOUNT)
    val amount: Int,

    @ColumnInfo(name = Config.COLUMN_REMARK)
    val remark: String
): Serializable {
}