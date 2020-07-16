package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.ParticularView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception
import kotlin.math.log

class ParticularPresenterIml(
    private var context: Context,
    private var view: ParticularView?
): ParticularPresenter {


    private val TAG = "ParticularPresenter"

    override fun fetchData(userId: Long) {

        try {
            val databaseManager = DatabaseManager.getInstance(context)!!

            view?.onParticularFetchSuccess(
                databaseManager?.getParticularDAO()?.getParticularsByUserId(userId)!!
            )
            
        }catch (e: Exception) {
            Log.d(TAG, "ParticularFetchException: ${e.message}")
        }
    }

    override fun saveData(
        propertyUtils: PropertyUtils,
        tenantUtils: TenantUtils,
        particularUtils: ParticularUtils
    ) {

        try {
            val databaseManager = DatabaseManager.getInstance(context)!!

            databaseManager.getParticularDAO().insert(particularUtils)

            if (particularUtils.transactionType == "Credit")
                tenantUtils.totalCredit += particularUtils.amount
            else
                tenantUtils.totalDebit += particularUtils.amount

            databaseManager.getTenantDAO().update(tenantUtils)

            propertyUtils.lastRant = getLastRant(
                propertyUtils.lastRant,
                particularUtils.month,
                particularUtils.year
            )

            Log.d(TAG, "Checking: " + getLastRant(
                "June, 2020",
                particularUtils.month,
                particularUtils.year
            ))




            Log.d(TAG, "SavingParticularData: $propertyUtils $tenantUtils and $particularUtils")

            databaseManager.getTenantDAO().update(tenantUtils)

            databaseManager.getPropertyDAO().update(propertyUtils)

            view?.onParticularAdd(true)
            
        }catch (e: Exception) {
            Log.d(TAG, "ParticularSavingException: ${e.message}")

            view?.onParticularAdd(false)
        }
    }

    private fun getLastRant(lastRant: String?, month: String, year: Int): String? {
        if(lastRant.isNullOrEmpty()) {
            return "$month, $year"
        }

        val values = lastRant.split(", ")

        val months = context.resources.getStringArray(R.array.months)
        val currentMonthIndex = months.indexOf(values[0])
        val newMonthIndex = months.indexOf(month)

        if (values[1].toInt() == year) {
            if (currentMonthIndex < newMonthIndex) {
                return "$month, $year"
            }
        } else if (values[1].toInt() < year) {
            return "$month, $year"
        }

        Log.d(TAG, "Current: $currentMonthIndex new: $newMonthIndex ${values[0]}:${values[1]}")

        return lastRant

    }

    override fun deleteData(tenantUtils: TenantUtils, particularUtils: ParticularUtils) {
        try {

            val databaseManager = DatabaseManager.getInstance(context)!!

            databaseManager.getParticularDAO().delete(particularUtils)

            var propertyUtils = databaseManager
                .getPropertyDAO().getPropertyById(tenantUtils.buildingId!!)


            if (particularUtils.transactionType == "Credit")
                tenantUtils.totalCredit -= particularUtils.amount
            else
                tenantUtils.totalDebit -= particularUtils.amount

            val maxLastRentMilli = databaseManager
                .getParticularDAO()
                .getMaxLastRentMilli(tenantUtils.id!!)

            val monthIndex = (maxLastRentMilli % 100).toInt()
            val year: Int = (maxLastRentMilli / 100).toInt()


            if (year == 0) {
                propertyUtils.lastRant = null
            } else {
                val months = context.resources.getStringArray(R.array.months)

                val month = months[monthIndex]

                propertyUtils.lastRant = getLastRant(
                    null,
                    month,
                    year
                )
            }

            Log.d(TAG, "UpdatedLastRant: ${propertyUtils.lastRant}")

            databaseManager.getPropertyDAO().update(propertyUtils)



            databaseManager.getTenantDAO().update(tenantUtils)

            view?.onParticularDelete(true)
            

        }catch (e: Exception) {
            Log.d(TAG, "ParticularDeletingException: ${e.message}")
            view?.onParticularDelete(false)
        }
    }
}