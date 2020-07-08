package org.binaryitplanet.rentalreminderapp.Features.Model

import androidx.room.*
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils

@Dao
interface PropertyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(property: PropertyUtils)


    @Update
    fun update(property: PropertyUtils)

    @Delete
    fun delete(property: PropertyUtils)
}