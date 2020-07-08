package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.PropertyView
import org.binaryitplanet.rentalreminderapp.Features.View.TenantView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception
import java.util.*

class TenantPresenterIml(
    private var context: Context,
    private var view: TenantView?
): TenantPresentar {

    private val TAG = "TenantPresenter"

    override fun fetchData(propertyStatus: Boolean) {
        super.fetchData(propertyStatus)
//
//        try {
//            val databaseManager = DatabaseManager.getInstance(context)
//
//
//            view?.onPropertyFetchSuccess(
//                databaseManager?.
//                getTenantDAO()?.
//                getAllTenants()!!
//            )
//        }catch (e: Exception) {
//            Log.d(TAG, "PropertyFetchingException: ${e.message}")
//        }

    }

    override fun saveData(tenantUtils: TenantUtils, propertyUtils: PropertyUtils) {
        super.saveData(tenantUtils, propertyUtils)

        try {

            Log.d(TAG, "SavingTenantData: $tenantUtils $propertyUtils")

            propertyUtils.renewDate = getRenewDate(tenantUtils.joinDate)


            Log.d(TAG, "SavingTenantData: $tenantUtils $propertyUtils")

            val databaseManager = DatabaseManager.getInstance(context)!!

            databaseManager
                .getPropertyDAO()
                .update(propertyUtils)
            databaseManager
                .getTenantDAO()
                .insert(tenantUtils)


            view?.onTenantAdd(true)
        }catch (e: Exception) {
            Log.d(TAG, "TenantSavingException: ${e.message}")

            view?.onTenantAdd(false)
        }
    }

    private fun getRenewDate(joinDate: String): String? {
        var values = joinDate.split("/")
        Log.d(TAG, "Split: $values")
        var month = values[1].toInt() + 11
        var year = values[2].toInt() + month / 12
        month %= 12
        Log.d(TAG, "Split: $month, $year")
//        return "%02d/%02d/%04d".format(values[0], values[1], values[2])
        return "%02d/%02d/%04d".format(values[0].toInt(), month, year)
    }

    override fun updateData(tenantUtils: TenantUtils) {
        super.updateData(tenantUtils)
//
//        try {
//            Log.d(TAG, "Updating: $tenantUtils")
//            val databaseManager = DatabaseManager.getInstance(context)
//
//            databaseManager?.getTenantDAO()?.update(tenantUtils)
//
//            view?.onPropertyUpdate(true)
//        }catch (e: Exception) {
//            Log.d(TAG, "PropertyUpdatingException: ${e.message}")
//
//            view?.onPropertyUpdate(false)
//        }
    }

    override fun buildingList() {
        super.buildingList()
//
//        try {
//            val databaseManager = DatabaseManager.getInstance(context)
//
//            view?.onBuildingListFetchSuccess(
//                databaseManager?.getTenantDAO()?.getAllBuildings()!!
//            )
//        }catch (e: Exception) {
//            Log.d(TAG, "BuildingFetchException: ${e.message}")
//        }
    }

    override fun fetchDataById(id: Long) {
        super.fetchDataById(id)
//        try {
//            val databaseManager = DatabaseManager.getInstance(context)
//
//            view?.onTenantFetchSuccess(
//                databaseManager?.getTenantDAO()?.getTenantById(id)!!
//            )
//        }catch (e: Exception) {
//            Log.d(TAG, "TenantFetchException: ${e.message}")
//        }
    }

    override fun totalRantReceivedThisMonth() {
        super.totalRantReceivedThisMonth()

//        try {
//            val databaseManager = DatabaseManager.getInstance(context)
//
//            val calendar = Calendar.getInstance()
//
//
//            val months = context.resources.getStringArray(R.array.months)
//
//            val month = months[calendar.get(Calendar.MONTH)]
//            val year = calendar.get(Calendar.YEAR)
//
//            val tenant = databaseManager?.getTenantDAO()?.getAllTenantsByType(true)
//
//            var credit = 0
//            var debit = 0
//
//            credit += databaseManager?.getParticularDAO()?.getSumOfCreditDebitByMonth(month, year, "Credit")!!
//            debit += databaseManager?.getParticularDAO()?.getSumOfCreditDebitByMonth(month, year, "Debit")!!
//
//
//
//            Log.d(TAG, "Month: $month Credit: $credit Debit: $debit")
//            view?.onTotalRantReceivedThisMonth(
//                credit - debit
//            )
//        }catch (e: Exception) {
//            Log.d(TAG, "TenantFetchException: ${e.message}")
//        }
    }

    override fun fetchDataByBuildingId(id: Long) {
        super.fetchDataByBuildingId(id)
        try{
            val databaseManager = DatabaseManager.getInstance(context)!!
            view?.onTenantFetchSuccessByBuildingId(
                databaseManager
                    .getTenantDAO()
                    .getTenantByBuildingId(id)
            )
        }catch (e: Exception) {
            Log.d(TAG, "TenantFetchingException: ${e.message}")
        }
    }

}