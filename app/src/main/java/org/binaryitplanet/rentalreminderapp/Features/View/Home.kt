package org.binaryitplanet.rentalreminderapp.Features.View

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
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.FragmentHomeBinding


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
        val tenantPresenterIml = TenantPresenterIml(context!!, this)
        tenantPresenterIml.fetchData(true)
        tenantPresenterIml.totalRantReceivedThisMonth()
    }


    override fun onPropertyFetchSuccess(tenantList: List<TenantUtils>) {
        super.onPropertyFetchSuccess(tenantList)
        Log.d(TAG, "TenantList: $tenantList")
        val propertyListAdapter = PropertyListAdapter(
            context!!,
            tenantList as ArrayList<TenantUtils>
        )

        binding.list.adapter = propertyListAdapter
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.setItemViewCacheSize(Config.LIST_CACHED_SIZE)
    }

    override fun onTotalRantReceivedThisMonth(totalRant: Int) {
        super.onTotalRantReceivedThisMonth(totalRant)
        binding.amount.text = totalRant.toString() +
                context?.resources?.getString(R.string.rupeeSign)
    }

}