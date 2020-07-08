package org.binaryitplanet.rentalreminderapp.Features.Adapter

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_property_list_item.view.*
import org.binaryitplanet.rentalreminderapp.Features.View.ViewOldProperty
import org.binaryitplanet.rentalreminderapp.Features.View.ViewProperty
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.OldTenantUtils
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import android.util.Pair as UtilPair

class OldTenantListAdapter(
    val context: Context,
    val oldTenantList: ArrayList<OldTenantUtils>
): RecyclerView.Adapter<OldTenantListAdapter.ViewHolder>() {

    private val TAG = "oldTenantListAdapter"

    // Creating view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.view_old_property_list_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = oldTenantList.size


    // Setting values into view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        Log.d(TAG, "Item: ${oldTenantList[position]}")
        view.listBuildingName.text = oldTenantList[position].buildingName
        view.currentTenantName.text = oldTenantList[position].tenantName
        view.currentTenantPhone.text = oldTenantList[position].phoneNumber
//        if (oldTenantList[position].propertyStatus) {
//            view.propertyStatus.text = Config.PROPERTY_STATUS_OCCUPIED
//            view.renewDate.text = Config.RENEW_DATE + oldTenantList[position].renewDate
//
//            if (oldTenantList[position].lastRant.isNullOrEmpty())
//                view.lastReceived.text = Config.LAST_RENT_EMPTY_MESSAGE
//            else
//                view.lastReceived.text = Config.LAST_RENT_MESSAGE + " " +
//                        oldTenantList[position].lastRant
//        } else {
//            view.propertyStatus.text = Config.PROPERTY_STATUS_UNOCCUPIED
//            view.currentTenantNameTitle.visibility = View.GONE
//            view.currentTenantName.visibility = View.GONE
//            view.currentTenantPhoneTitle.visibility = View.GONE
//            view.currentTenantPhone.visibility = View.GONE
//            view.lastReceived.visibility = View.GONE
//            view.renewDate.visibility = View.GONE
//        }


        view.setOnClickListener {
            Log.d(TAG, "PropertyClicked: $position")

            var intent = Intent(context, ViewOldProperty::class.java)

            // Passing selected item data
            intent.putExtra(Config.PROPERTY_INFORMATION, oldTenantList[position])

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Creating animation options
            val options =
                ActivityOptions
                    .makeSceneTransitionAnimation(
                        context as Activity,
                        UtilPair.create(
                            view.listBuildingName,
                            context.resources.getString(R.string.buildingNameTransition)
                        ),
                        UtilPair.create(
                            view.currentTenantName,
                            context.resources.getString(R.string.tenantNameTransition)
                        ),
                        UtilPair.create(
                            view.currentTenantPhone,
                            context.resources.getString(R.string.mobileNumberTransition)
                        )
                    )
                context.window.exitTransition = null
                context.startActivity(intent, options.toBundle())
            } else {
                context.startActivity(intent)
            }

        }
    }



    // Holding the view
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}