package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface TenantView {

    fun onTenantFetchSuccess(tenantList: List<TenantUtils>) {}
    fun onTenantFetchSuccessByBuildingId(tenant: TenantUtils) {}
    fun onTenantAdd(status: Boolean){}
    fun onTenantUpdate(status: Boolean){}

}