package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.OldTenantView
import org.binaryitplanet.rentalreminderapp.Features.View.PropertyView
import org.binaryitplanet.rentalreminderapp.Features.View.TenantView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.OldTenantUtils
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception
import java.util.*

class OldTenantPresenterIml(
    private var context: Context,
    private var view: OldTenantView?
): OldTenantPresentar {

    private val TAG = "TenantPresenter"

    override fun fetchData() {
        super.fetchData()

        try {
            val databaseManager = DatabaseManager.getInstance(context)

            Log.d(TAG, "OldTenantFetching:")

            view?.onFetchOldTenantList(
                databaseManager?.
                getOldTenantDAO()?.
                getAllOldTenant()!!
            )


        }catch (e: Exception) {
            Log.d(TAG, "OldTenantFetchingException: ${e.message}")
        }

    }

    override fun deleteData(
        oldTenantUtils: OldTenantUtils,
        propertyUtils: PropertyUtils,
        tenantUtils: TenantUtils
    ) {
        super.deleteData(oldTenantUtils, propertyUtils, tenantUtils)

        try {




            val databaseManager = DatabaseManager.getInstance(context)!!

            propertyUtils.tenantName = null
            propertyUtils.phoneNumber = null
            propertyUtils.propertyStatus = false
            propertyUtils.lastRant = null
            propertyUtils.renewDate = null

            databaseManager.getPropertyDAO().update(propertyUtils)

            databaseManager.getTenantDAO().delete(tenantUtils)

            databaseManager
                .getOldTenantDAO()
                .insert(oldTenantUtils)


            view?.onDeleteOldTenant(true)
            Log.d(TAG, "DeletingOldTenantData:  $oldTenantUtils")

        }catch (e: Exception) {
            Log.d(TAG, "OldTenantDeleteException: ${e.message}")

            view?.onDeleteOldTenant(false)
        }
    }

    override fun saveData(oldTenantUtils: OldTenantUtils) {
        super.saveData(oldTenantUtils)

        try {

            Log.d(TAG, "SavingOldTenantData:  $oldTenantUtils")



            val databaseManager = DatabaseManager.getInstance(context)!!

            databaseManager
                .getOldTenantDAO()
                .insert(oldTenantUtils)


            view?.onSaveOldTenant(true)
        }catch (e: Exception) {
            Log.d(TAG, "OldTenantSavingException: ${e.message}")

            view?.onSaveOldTenant(false)
        }
    }

}