package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.PropertyView
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception

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

            view?.onPropertyAdd(false)
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

            view?.onPropertyAdd(true)
        }catch (e: Exception) {
            Log.d(TAG, "PropertyUpdatingException: ${e.message}")

            view?.onPropertyAdd(false)
        }
    }
}