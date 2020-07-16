package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.OldTenantUtils

interface OldTenantView {
    fun onSaveOldTenant(status: Boolean){}
    fun onDeleteOldTenant(status: Boolean){}
    fun onRestoreOldTenant(status: Boolean, message: String){}
    fun onFetchOldTenantList(oldTenantList: List<OldTenantUtils>){}
    fun onFetchOldTenant(oldTenant: OldTenantUtils){}
}