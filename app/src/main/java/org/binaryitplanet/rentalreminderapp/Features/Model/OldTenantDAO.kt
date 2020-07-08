package org.binaryitplanet.rentalreminderapp.Features.Model

import androidx.room.*
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils


@Dao
interface OldTenantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tenant: TenantUtils)


    @Update
    fun update(tenant: TenantUtils)

    @Delete
    fun delete(tenant: TenantUtils)
}