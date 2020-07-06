package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.PropertyView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception
import java.util.*

class TenantPresenterIml(
    private var context: Context,
    private var view: PropertyView?
): TenantPresentar {

    private val TAG = "TenantPresenter"

    override fun fetchData(propertyStatus: Boolean) {
        super.fetchData(propertyStatus)

        try {
            val databaseManager = DatabaseManager.getInstance(context)


            view?.onPropertyFetchSuccess(
                databaseManager?.
                getTenantDAO()?.
                getAllTenantsByType(propertyStatus)!!
            )
        }catch (e: Exception) {
            Log.d(TAG, "PropertyFetchingException: ${e.message}")
        }

    }

    override fun saveData(tenantUtils: TenantUtils) {
        super.saveData(tenantUtils)

        try {
            Log.d(TAG, "SavingTenantData: $tenantUtils")
            val databaseManager = DatabaseManager.getInstance(context)

            databaseManager?.getTenantDAO()?.insert(tenantUtils)

            view?.onPropertyAdd(true)
        }catch (e: Exception) {
            Log.d(TAG, "PropertySavingException: ${e.message}")

            view?.onPropertyAdd(false)
        }
    }

    override fun updateData(tenantUtils: TenantUtils) {
        super.updateData(tenantUtils)

        try {
            Log.d(TAG, "Updating: $tenantUtils")
            val databaseManager = DatabaseManager.getInstance(context)

            databaseManager?.getTenantDAO()?.update(tenantUtils)

            view?.onPropertyUpdate(true)
        }catch (e: Exception) {
            Log.d(TAG, "PropertyUpdatingException: ${e.message}")

            view?.onPropertyUpdate(false)
        }
    }

    override fun buildingList() {
        super.buildingList()

        try {
            val databaseManager = DatabaseManager.getInstance(context)

            view?.onBuildingListFetchSuccess(
                databaseManager?.getTenantDAO()?.getAllBuildings()!!
            )
        }catch (e: Exception) {
            Log.d(TAG, "BuildingFetchException: ${e.message}")
        }
    }

    override fun fetchDataById(id: Long) {
        super.fetchDataById(id)
        try {
            val databaseManager = DatabaseManager.getInstance(context)

            view?.onTenantFetchSuccess(
                databaseManager?.getTenantDAO()?.getTenantById(id)!!
            )
        }catch (e: Exception) {
            Log.d(TAG, "TenantFetchException: ${e.message}")
        }
    }

    override fun totalRantReceivedThisMonth() {
        super.totalRantReceivedThisMonth()

        try {
            val databaseManager = DatabaseManager.getInstance(context)

            val calendar = Calendar.getInstance()


            val months = context.resources.getStringArray(R.array.months)

            val month = months[calendar.get(Calendar.MONTH)]
            val year = calendar.get(Calendar.YEAR)

            val tenant = databaseManager?.getTenantDAO()?.getAllTenantsByType(true)

            var credit = 0
            var debit = 0

            tenant?.forEach {
                credit += databaseManager?.getParticularDAO()?.getSumOfCreditDebitByMonth(month, year, "Credit", it.id!!)!!
                debit += databaseManager?.getParticularDAO()?.getSumOfCreditDebitByMonth(month, year, "Debit", it.id!!)!!
            }



            Log.d(TAG, "Month: $month Credit: $credit Debit: $debit")
            view?.onTotalRantReceivedThisMonth(
                credit - debit
            )
        }catch (e: Exception) {
            Log.d(TAG, "TenantFetchException: ${e.message}")
        }
    }

}