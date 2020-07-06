package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils

interface ParticularView {
    fun onPerticularFetchSuccess(particularList: List<ParticularUtils>) {}
    fun onPerticularAdd(status: Boolean){}
}