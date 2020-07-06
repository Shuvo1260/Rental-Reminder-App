package org.binaryitplanet.rentalreminderapp.Features.Model

import androidx.room.*
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

@Dao
interface ParticularDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(particular: ParticularUtils)


    @Update
    fun update(particular: ParticularUtils)

    @Delete
    fun delete(particular: ParticularUtils)

    @Query("SELECT * FROM Particulars WHERE User_ID == :userId ORDER BY ID DESC")
    fun getParticularsByUserId(userId: Long): List<ParticularUtils>


    @Query("SELECT * FROM Particulars")
    fun getAllParticulars(): List<ParticularUtils>



    @Query("SELECT SUM(Amount) FROM Particulars WHERE Month == :month AND Year == :year AND Transaction_type == :type AND User_ID == :userId")
    fun getSumOfCreditDebitByMonth(month: String, year: Int, type: String, userId: Long): Int


}