package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils

interface ParticularView {
    fun onParticularFetchSuccess(particularList: List<ParticularUtils>) {}
    fun onParticularAdd(status: Boolean){}
    fun onParticularDeleteClick(position: Int){}
    fun onParticularDelete(status: Boolean){}
}