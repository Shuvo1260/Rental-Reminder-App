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

    @Query("SELECT * FROM Property ORDER BY Building_Name ASC")
    fun getAllProperty(): List<PropertyUtils>

    @Query("SELECT * FROM Property WHERE ID == :id")
    fun getPropertyById(id: Long): PropertyUtils
}