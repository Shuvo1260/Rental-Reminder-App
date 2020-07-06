package org.binaryitplanet.rentalreminderapp.Features.Presentar

import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

interface ParticularPresenter {
    fun fetchData(userId: Long)
    fun saveData(tenantUtils: TenantUtils, particularUtils: ParticularUtils)
}