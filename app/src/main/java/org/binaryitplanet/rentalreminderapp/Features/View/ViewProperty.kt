package org.binaryitplanet.rentalreminderapp.Features.View

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.binaryitplanet.rentalreminderapp.Features.Adapter.ParticularListAdapter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.OldTenantPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.ParticularPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.PropertyPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.*
import org.binaryitplanet.rentalreminderapp.databinding.ActivityViewPropertyBinding
import java.lang.Exception

class ViewProperty : AppCompatActivity(), PropertyView, ParticularView, TenantView, OldTenantView {

    private val TAG = "ViewProperty"

    private lateinit var binding: ActivityViewPropertyBinding

    private var tenantUtils: TenantUtils? = null
    private lateinit var propertyUtils: PropertyUtils
    private lateinit var particularList: ArrayList<ParticularUtils>

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
                if (tenantUtils == null) {
                    Toast.makeText(
                        this,
                        Config.NO_TENANT,
                        Toast.LENGTH_SHORT
                    ).show()
                }else
                    leave()
            } else if(it.itemId == R.id.editProperty) {
                editProperty()
            } else if (it.itemId == R.id.deleteProperty) {
                deleteProperty()
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

        // Making phone call on phone number click
        binding.currentTenantPhone.setOnClickListener {
            // Calling phone call method
            makeCall()
        }
    }

    // Making a phone call
    private fun makeCall() {
        if (!tenantUtils?.phoneNumber.isNullOrEmpty()) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${tenantUtils?.phoneNumber}")
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE), Config.REQUEST_CALL)
            } else {
                startActivity(intent)
            }
        }
    }

    // Deleting property permanently
    private fun deleteProperty() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle(Config.DELETE_PROPERTY_TITLE)
        builder.setMessage(Config.DELETE_PROPERTY_MESSAGE)

        builder.setIcon(R.drawable.ic_launcher)

        builder.setPositiveButton(
            Config.YES_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->

            try {

                Log.d(TAG, "DeletingProperty")

                var propertyPresenter = PropertyPresenterIml(this, this)
                propertyPresenter.deleteData(propertyUtils, tenantUtils)

                Log.d(TAG, "DeletingProperty")
            }catch (e: Exception) {
                Log.d(TAG, "DeletingException: ${e.message}")
            }
        }

        builder.setNegativeButton(
            Config.NO_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onPropertyDelete(status: Boolean) {
        super.onPropertyDelete(status)

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

    private fun editProperty() {
        val intent = Intent(this, AddProperty::class.java)
        intent.putExtra(Config.PROPERTY_EDIT_FLAG, true)
        intent.putExtra(Config.PROPERTY_INFORMATION, propertyUtils)
        startActivity(intent)
        overridePendingTransition(R.anim.lefttoright, R.anim.righttoleft)
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


    override fun onParticularFetchSuccess(particularList: List<ParticularUtils>) {
        super.onParticularFetchSuccess(particularList)
        Log.d(TAG, "ParticularList: $particularList")


        this.particularList = particularList as ArrayList<ParticularUtils>
        val particularAdapter = ParticularListAdapter(
            this,
            this.particularList,
            true,
            this
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
        Log.d(TAG, "Leaving ")

        val builder = AlertDialog.Builder(this)

        builder.setTitle(Config.LEAVING_TENANT_TITLE)
        builder.setMessage(Config.LEAVING_TENANT_MESSAGE)

        builder.setIcon(R.drawable.ic_launcher)

        builder.setPositiveButton(
            Config.YES_MESSAGE
        ){
            dialog: DialogInterface?, which: Int ->

            var oldTenant = OldTenantUtils(
                null,
                tenantUtils?.id,
                propertyUtils.buildingName,
                propertyUtils.address!!,
                tenantUtils?.tenantName!!,
                tenantUtils?.phoneNumber!!,
                tenantUtils?.joinDate!!,
                tenantUtils?.idProof!!,
                tenantUtils?.totalDebit!!,
                tenantUtils?.totalCredit!!

            )


            val presenter = OldTenantPresenterIml(this, this)
            presenter.deleteData(oldTenant, propertyUtils, tenantUtils!!)
        }

        builder.setNegativeButton(
            Config.NO_MESSAGE
        ){
            dialog: DialogInterface?, which: Int ->
        }

        val alertDialog = builder.create()
        alertDialog.show()

    }

    override fun onParticularDeleteClick(position: Int) {
        super.onParticularDeleteClick(position)
        Log.d(TAG, "DeleteParticular: $position")
        val builder = AlertDialog.Builder(this)

        builder.setTitle(Config.DELETE_PARTICULAR_TITLE)
        builder.setMessage(Config.DELETE_PARTICULAR_MESSAGE)

        builder.setIcon(R.drawable.ic_launcher)

        builder.setPositiveButton(
            Config.YES_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->



            val presenter = ParticularPresenterIml(this, this)
            presenter.deleteData(tenantUtils!!, particularList[position])
        }

        builder.setNegativeButton(
            Config.NO_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    override fun onParticularDelete(status: Boolean) {
        super.onParticularDelete(status)
        if (status) {
            Toast.makeText(
                this,
                Config.SUCCESS_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
            fetchPropertyData(propertyUtils.id)
        } else {
            Toast.makeText(
                this,
                Config.FAILED_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDeleteOldTenant(status: Boolean) {
        super.onDeleteOldTenant(status)
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