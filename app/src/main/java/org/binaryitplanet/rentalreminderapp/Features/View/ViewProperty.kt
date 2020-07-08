package org.binaryitplanet.rentalreminderapp.Features.View

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.binaryitplanet.rentalreminderapp.Features.Adapter.ParticularListAdapter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.ParticularPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.PropertyPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityViewPropertyBinding

class ViewProperty : AppCompatActivity(), PropertyView, ParticularView, TenantView {

    private val TAG = "ViewProperty"

    private lateinit var binding: ActivityViewPropertyBinding

    private var tenantUtils: TenantUtils? = null
    private lateinit var propertyUtils: PropertyUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_property)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = null
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_property)

        setUpToolbar()

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.leave) {
                leave()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }

        binding.add.setOnClickListener {
            if (tenantUtils == null) {
                val intent = Intent(applicationContext, AddTenant::class.java)
                intent.putExtra(Config.PROPERTY_INFORMATION, propertyUtils)
                startActivity(intent)
                overridePendingTransition(R.anim.lefttoright, R.anim.lefttoright)
            } else {
                val intent = Intent(applicationContext, AddParticulars::class.java)
                intent.putExtra(Config.PROPERTY_INFORMATION, propertyUtils)
                intent.putExtra(Config.TENANT_INFORMATION, tenantUtils)
                startActivity(intent)
                overridePendingTransition(R.anim.lefttoright, R.anim.lefttoright)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        propertyUtils = intent.getSerializableExtra(Config.PROPERTY_INFORMATION) as PropertyUtils
        fetchPropertyData(propertyUtils.id)
    }

    private fun fetchPropertyData(id: Long?) {
        val propertyPresenterIml = PropertyPresenterIml(this, this)
        propertyPresenterIml.fetchDataById(id!!)
    }

    override fun onPropertyFetchSuccessById(property: PropertyUtils) {
        super.onPropertyFetchSuccessById(property)
        propertyUtils = property
        if (property.tenantName.isNullOrEmpty()) {
            setPropertyViews()
        } else {
            fetchTenantData(property.id)
        }
    }


    private fun fetchTenantData(id: Long?) {

        val tenantPresenterIml = TenantPresenterIml(this, this)
        tenantPresenterIml.fetchDataByBuildingId(id!!)
    }

    override fun onTenantFetchSuccessByBuildingId(tenant: TenantUtils) {
        super.onTenantFetchSuccessByBuildingId(tenant)
        tenantUtils = tenant
        setViews()
        val particularPresenterIml = ParticularPresenterIml(this, this)
        particularPresenterIml.fetchData(tenantUtils!!.id!!)
    }


    override fun onPerticularFetchSuccess(particularList: List<ParticularUtils>) {
        super.onPerticularFetchSuccess(particularList)
        Log.d(TAG, "ParticularList: $particularList")

        val particularAdapter = ParticularListAdapter(
            this,
            particularList as ArrayList<ParticularUtils>
        )
        binding.list.adapter = particularAdapter
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.setItemViewCacheSize(Config.LIST_CACHED_SIZE)
    }

    private fun setPropertyViews() {
        binding.navigationTitle.text = propertyUtils.buildingName
        binding.address.text = propertyUtils.address
        if (propertyUtils.propertyStatus)
            binding.propertyStatus.text = Config.PROPERTY_STATUS_OCCUPIED
        else
            binding.propertyStatus.text = Config.PROPERTY_STATUS_UNOCCUPIED
    }
    // Setting views
    private fun setViews() {
        binding.navigationTitle.text = propertyUtils.buildingName
        binding.currentTenantName.text = tenantUtils!!.tenantName
        binding.currentTenantPhone.text = tenantUtils!!.phoneNumber
        binding.totalDebit.text = tenantUtils!!.totalDebit.toString()
        binding.totalCredit.text = tenantUtils!!.totalCredit.toString()
        binding.idProof.text = tenantUtils!!.idProof
        binding.address.text = propertyUtils.address

        if (propertyUtils.propertyStatus)
            binding.propertyStatus.text = Config.PROPERTY_STATUS_OCCUPIED
        else
            binding.propertyStatus.text = Config.PROPERTY_STATUS_UNOCCUPIED
    }

    private fun leave() {
//        Log.d(TAG, "Leaving ")
//
//        val builder = AlertDialog.Builder(this)
//
//        builder.setTitle(Config.LEAVING_TENANT_TITLE)
//        builder.setMessage(Config.LEAVING_TENANT_MESSAGE)
//
//        builder.setIcon(R.drawable.ic_launcher)
//
//        builder.setPositiveButton(
//            Config.YES_MESSAGE
//        ){
//            dialog: DialogInterface?, which: Int ->
//
//            tenantUtils.propertyStatus = false
//
//            val presenter = TenantPresenterIml(this, this)
//            presenter.updateData(tenantUtils)
//        }
//
//        builder.setNegativeButton(
//            Config.NO_MESSAGE
//        ){
//            dialog: DialogInterface?, which: Int ->
//        }
//
//        val alertDialog = builder.create()
//        alertDialog.show()

    }

    // Marking as old tenant
    override fun onPropertyUpdate(status: Boolean) {
        super.onPropertyUpdate(status)
        if (status) {
            Toast.makeText(
                this,
                Config.SUCCESS_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
            onBackPressed()
        } else {
            Toast.makeText(
                this,
                Config.FAILED_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    // Toolbar menu setting
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.leave_menu, menu)
        return super.onCreateOptionsMenu(menu)
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