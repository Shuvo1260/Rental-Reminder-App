package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.PropertyView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class PropertyPresenterIml(
    private var context: Context,
    private var view: PropertyView?
): PropertyPresentar {

    private val TAG = "PropertyPresenterIml"

    override fun fetchData(propertyStatus: Boolean) {
        try{
            var databaseManager = DatabaseManager.getInstance(context)



            view?.onPropertyFetchSuccess(
                databaseManager
                    ?.getPropertyDAO()
                    ?.getAllProperty()!!
            )

            Log.d(TAG, "PropertyFetchingSuccess: ")
        }catch (e: Exception) {
            Log.d(TAG, "PropertyFetchingError: ${e.message}")
        }
    }

    override fun saveData(propertyUtils: PropertyUtils) {
        try{
            var databaseManager = DatabaseManager.getInstance(context)

            databaseManager?.getPropertyDAO()?.insert(propertyUtils)

            view?.onPropertyAdd(true)

            Log.d(TAG, "PropertySavingSuccess: $propertyUtils")
        }catch (e: Exception) {
            Log.d(TAG, "PropertySavingError: ${e.message}")
            view?.onPropertyAdd(false)
        }
    }

    override fun updateData(propertyUtils: PropertyUtils) {
        //
    }

    override fun fetchDataById(id: Long) {
        try{
            var databaseManager = DatabaseManager.getInstance(context)



            view?.onPropertyFetchSuccessById(
                databaseManager
                    ?.getPropertyDAO()
                    ?.getPropertyById(id)!!
            )

            Log.d(TAG, "PropertyFetchingSuccess: ")
        }catch (e: Exception) {
            Log.d(TAG, "PropertyFetchingError: ${e.message}")
        }
    }

    override fun totalRantReceivedThisMonth() {

        try {
            val databaseManager = DatabaseManager.getInstance(context)!!

            val calendar = Calendar.getInstance()


            val months = context.resources.getStringArray(R.array.months)

            val month = months[calendar.get(Calendar.MONTH)]
            val year = calendar.get(Calendar.YEAR)


            var credit = 0
            var debit = 0

            credit = databaseManager.getParticularDAO().getSumOfCreditDebitByMonth(month, year, "Credit")
            debit = databaseManager.getParticularDAO().getSumOfCreditDebitByMonth(month, year, "Debit")



            Log.d(TAG, "Month: $month Credit: $credit Debit: $debit")
            view?.onTotalRantReceivedThisMonth(
                credit - debit
            )
        }catch (e: Exception) {
            Log.d(TAG, "TenantFetchException: ${e.message}")
        }
    }


}