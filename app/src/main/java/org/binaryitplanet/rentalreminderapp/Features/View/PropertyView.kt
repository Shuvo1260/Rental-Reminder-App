package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface PropertyView {
    fun onPropertyFetchSuccess(tenantList: List<TenantUtils>) {}
    fun onPropertyAdd(status: Boolean){}
    fun onPropertyUpdate(status: Boolean){}
}