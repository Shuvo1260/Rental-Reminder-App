package org.binaryitplanet.rentalreminderapp.Features.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import org.binaryitplanet.rentalreminderapp.Features.Adapter.PropertyListAdapter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.PropertyPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Services.ReminderManager
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.ReminderUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.FragmentHomeBinding
import java.util.*
import kotlin.collections.ArrayList


class Home : Fragment(), PropertyView {

    private val TAG = "Home"

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.add.setOnClickListener {
            val intent = Intent(context, AddProperty::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        val propertyPresenterIml = PropertyPresenterIml(context!!, this)
        propertyPresenterIml.fetchData(true)
        propertyPresenterIml.totalRantReceivedThisMonth()
    }


    override fun onPropertyFetchSuccess(propertyList: List<PropertyUtils>) {
        super.onPropertyFetchSuccess(propertyList)
        Log.d(TAG, "propertyList: $propertyList")

        val propertyListAdapter = PropertyListAdapter(
            context!!,
            propertyList as ArrayList<PropertyUtils>
        )



        binding.list.adapter = propertyListAdapter
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setItemViewCacheSize(Config.LIST_CACHED_SIZE)

        if (propertyList.isNotEmpty())
            checkReminder()
    }

    private fun checkReminder() {
        val sharedPreferences = context?.getSharedPreferences(
            Config.SHARED_PREFERENCE,
            Context.MODE_PRIVATE
        )
        val firstReminder = sharedPreferences!!.getBoolean(
            Config.FIRST_REMINDER,
            false
        )
        if (!firstReminder){

            setFirstReminder()

            val editor = sharedPreferences.edit()
            editor.putBoolean(Config.FIRST_REMINDER, true)
            editor.apply()
            editor.commit()
        }
    }

    private fun setFirstReminder() {
        Log.d(TAG, "FirstReminderSetup")
        val day = 7
        var month = Calendar.getInstance().get(Calendar.MONTH)
        var year = Calendar.getInstance().get(Calendar.YEAR)

        if (month == 11) {
            month = 0
            year++
        }
        else
            month++

        val reminderUtils = ReminderUtils(day, month, year)
        val reminderManager = ReminderManager(context!!, reminderUtils)
        reminderManager.create()
        reminderManager.setReminder()

    }

    override fun onTotalRantReceivedThisMonth(totalRant: Int) {
        super.onTotalRantReceivedThisMonth(totalRant)
        binding.amount.text = totalRant.toString() +
                context?.resources?.getString(R.string.rupeeSign)
    }

}