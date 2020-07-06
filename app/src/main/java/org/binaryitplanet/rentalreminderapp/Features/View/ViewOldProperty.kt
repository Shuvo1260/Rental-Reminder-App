package org.binaryitplanet.rentalreminderapp.Features.View

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.databinding.DataBindingUtil
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityViewOldPropertyBinding

class ViewOldProperty : AppCompatActivity() {

    private val TAG = "ViewProperty"

    private lateinit var binding: ActivityViewOldPropertyBinding

    private lateinit var tenantUtils: TenantUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_old_property)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = null
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_old_property)

        setUpToolbar()
    }

    override fun onResume() {
        super.onResume()
        tenantUtils = intent.getSerializableExtra(Config.PROPERTY_INFORMATION) as TenantUtils

        setViews()
    }

    // Setting views
    private fun setViews() {
        binding.navigationTitle.text = tenantUtils.buildingName
        binding.currentTenantName.text = tenantUtils.tenantName
        binding.currentTenantPhone.text = tenantUtils.phoneNumber
        binding.totalDebit.text = tenantUtils.totalDebit.toString()
        binding.totalCredit.text = tenantUtils.totalCredit.toString()
        binding.idProof.text = tenantUtils.idProof

        if (tenantUtils.propertyStatus)
            binding.propertyStatus.text = Config.PROPERTY_STATUS_OCCUPIED
        else
            binding.propertyStatus.text = Config.PROPERTY_STATUS_UNOCCUPIED
    }


    private fun setUpToolbar() {
        binding.toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "Back pressed")
        onBackPressed()
        return true
    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.lefttoright, R.anim.righttoleft)
    }
}