package org.binaryitplanet.rentalreminderapp.Features.Model

import androidx.room.*
import org.binaryitplanet.rentalreminderapp.Utils.OldTenantUtils


@Dao
interface OldTenantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(oldTenant: OldTenantUtils)


    @Update
    fun update(oldTenant: OldTenantUtils)

    @Delete
    fun delete(oldTenant: OldTenantUtils)

    @Query("SELECT * FROM Old_Tenants ORDER BY Building_Name ASC")
    fun getAllOldTenant(): List<OldTenantUtils>
}