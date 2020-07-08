package org.binaryitplanet.rentalreminderapp.Features.Presentar

import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface TenantPresentar {
    fun fetchData(propertyStatus: Boolean){}
    fun saveData(tenantUtils: TenantUtils, propertyUtils: PropertyUtils){}
    fun updateData(tenantUtils: TenantUtils){}
    fun fetchDataById(id: Long){}
    fun fetchDataByBuildingId(id: Long){}

    fun totalRantReceivedThisMonth(){}

    fun buildingList(){}
}