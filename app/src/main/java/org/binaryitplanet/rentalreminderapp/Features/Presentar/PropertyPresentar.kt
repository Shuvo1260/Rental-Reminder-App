package org.binaryitplanet.rentalreminderapp.Features.Presentar

import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface PropertyPresentar {
    fun fetchData(propertyStatus: Boolean)
    fun deleteData(
        propertyUtils: PropertyUtils,
        tenantUtils: TenantUtils?
    )
    fun saveData(propertyUtils: PropertyUtils)
    fun updateData(propertyUtils: PropertyUtils)
    fun fetchDataById(id: Long)

    fun totalRantReceivedThisMonth()
}