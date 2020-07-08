package org.binaryitplanet.rentalreminderapp.Features.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.binaryitplanet.rentalreminderapp.Features.Adapter.PropertyListAdapter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.FragmentOldTenantBinding

class OldTenant : Fragment(), PropertyView{

    private val TAG = "OldTenant"

    private lateinit var binding: FragmentOldTenantBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOldTenantBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        val tenantPresenterIml = TenantPresenterIml(context!!, this)
        tenantPresenterIml.fetchData(false)
    }


    override fun onPropertyFetchSuccess(tenantList: List<PropertyUtils>) {
        super.onPropertyFetchSuccess(tenantList)
        Log.d(TAG, "TenantList: $tenantList")
//        val propertyListAdapter = PropertyListAdapter(
//            context!!,
//            tenantList as ArrayList<TenantUtils>
//        )

//        binding.list.adapter = propertyListAdapter
//        binding.list.layoutManager = LinearLayoutManager(context)
//        binding.list.setItemViewCacheSize(Config.LIST_CACHED_SIZE)
    }


}