package org.binaryitplanet.rentalreminderapp.Features.Presentar

import android.content.Context
import android.util.Log
import org.binaryitplanet.rentalreminderapp.Features.Model.DatabaseManager
import org.binaryitplanet.rentalreminderapp.Features.View.ParticularView
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import java.lang.Exception

class ParticularPresenterIml(
    private var context: Context,
    private var view: ParticularView?
): ParticularPresenter {


    private val TAG = "ParticularPresenter"

    override fun fetchData(userId: Long) {

        try {
            val databaseManager = DatabaseManager.getInstance(context)

            view?.onPerticularFetchSuccess(
                databaseManager?.getParticularDAO()?.getParticularsByUserId(userId)!!
            )
        }catch (e: Exception) {
            Log.d(TAG, "ParticularFetchException: ${e.message}")
        }
    }

    override fun saveData(tenantUtils: TenantUtils, particularUtils: ParticularUtils) {

        try {
            val databaseManager = DatabaseManager.getInstance(context)

            databaseManager?.getParticularDAO()?.insert(particularUtils)

            if (particularUtils.transactionType == "Credit")
                tenantUtils.totalCredit += particularUtils.amount
            else
                tenantUtils.totalDebit += particularUtils.amount

            tenantUtils.lastRant = particularUtils.month + ", " +
                    particularUtils.year


            Log.d(TAG, "SavingParticularData: $tenantUtils and $particularUtils")

            databaseManager?.getTenantDAO()?.update(tenantUtils)

            view?.onPerticularAdd(true)
        }catch (e: Exception) {
            Log.d(TAG, "ParticularSavingException: ${e.message}")

            view?.onPerticularAdd(false)
        }
    }
}