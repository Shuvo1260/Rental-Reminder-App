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
import org.binaryitplanet.rentalreminderapp.Features.View.ViewProperty
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import android.util.Pair as UtilPair

class PropertyListAdapter(
    val context: Context,
    val tenantList: ArrayList<TenantUtils>
): RecyclerView.Adapter<PropertyListAdapter.ViewHolder>() {

    private val TAG = "PropertyListAdapter"

    // Creating view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(context)
                .inflate(
                    R.layout.view_property_list_item,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = tenantList.size


    // Setting values into view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val view = holder.itemView
        Log.d(TAG, "Item: ${tenantList[position]}")
        view.listBuildingName.text = tenantList[position].buildingName
        view.currentTenantName.text = tenantList[position].tenantName
        view.currentTenantPhone.text = tenantList[position].phoneNumber
        view.details.text = tenantList[position].comment

        if (!tenantList[position].propertyStatus)
            view.currentTenantNameTitle.text = Config.PREVIOUS_TENANT_TITLE

        if (tenantList[position].lastRant.isNullOrEmpty())
            view.lastReceived.text = Config.LAST_RANT_EMPTY_MESSAGE
        else
            view.lastReceived.text = Config.LAST_RANT_MESSAGE +
                    tenantList[position].lastRant

        view.setOnClickListener {
            Log.d(TAG, "PropertyClicked: $position")

            val intent = Intent(context, ViewProperty::class.java)

            // Passing selected item data
            intent.putExtra(Config.PROPERTY_INFORMATION, tenantList[position])

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