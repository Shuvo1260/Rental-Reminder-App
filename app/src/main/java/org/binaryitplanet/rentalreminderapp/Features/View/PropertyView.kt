package org.binaryitplanet.rentalreminderapp.Features.View

import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils

interface PropertyView {
    fun onPropertyFetchSuccess(propertyList: List<PropertyUtils>) {}
    fun onPropertyFetchSuccessById(property: PropertyUtils) {}
    fun onPropertyAdd(status: Boolean){}
    fun onPropertyUpdate(status: Boolean){}

    fun onPropertyDelete(status: Boolean){}

    fun onTotalRantReceivedThisMonth(totalRant: Int){}
}