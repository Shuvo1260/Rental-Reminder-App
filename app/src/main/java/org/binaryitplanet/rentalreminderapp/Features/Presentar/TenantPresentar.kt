package org.binaryitplanet.rentalreminderapp.Features.Presentar

import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface TenantPresentar {
    fun fetchData(propertyStatus: Boolean){}
    fun saveData(tenantUtils: TenantUtils){}
    fun updateData(tenantUtils: TenantUtils){}

    fun buildingList(){}
}