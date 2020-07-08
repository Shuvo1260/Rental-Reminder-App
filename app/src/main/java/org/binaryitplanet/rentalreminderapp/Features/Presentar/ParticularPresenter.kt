package org.binaryitplanet.rentalreminderapp.Features.Presentar

import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface ParticularPresenter {
    fun fetchData(userId: Long)
    fun saveData(propertyUtils: PropertyUtils, tenantUtils: TenantUtils, particularUtils: ParticularUtils)
}