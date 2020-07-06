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
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityViewPropertyBinding

class ViewProperty : AppCompatActivity(), PropertyView {

    private val TAG = "ViewProperty"

    private lateinit var binding: ActivityViewPropertyBinding

    private lateinit var tenantUtils: TenantUtils

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
            val intent = Intent(applicationContext, AddParticulars::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.lefttoright, R.anim.lefttoright)
        }
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

            tenantUtils.propertyStatus = false

            val presenter = TenantPresenterIml(this, this)
            presenter.updateData(tenantUtils)
        }

        builder.setNegativeButton(
            Config.NO_MESSAGE
        ){
            dialog: DialogInterface?, which: Int ->
        }

        val alertDialog = builder.create()
        alertDialog.show()

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