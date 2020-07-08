package org.binaryitplanet.rentalreminderapp.Features.View

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import org.binaryitplanet.rentalreminderapp.Features.Adapter.ParticularListAdapter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.OldTenantPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.ParticularPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.OldTenantUtils
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityViewOldPropertyBinding

class ViewOldProperty : AppCompatActivity(), ParticularView, OldTenantView {

    private val TAG = "ViewProperty"

    private lateinit var binding: ActivityViewOldPropertyBinding

    private lateinit var tenantUtils: OldTenantUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_old_property)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.enterTransition = null
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_old_property)

        setUpToolbar()


        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.delete) {
                    delete()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }
    }

    private fun delete() {

        val builder = AlertDialog.Builder(this)

        builder.setTitle(Config.DELETE_TENANT_TITLE)
        builder.setMessage(Config.DELETE_TENANT_MESSAGE)

        builder.setIcon(R.drawable.ic_launcher)

        builder.setPositiveButton(
            Config.YES_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->

            var oldTenantPresenterIml = OldTenantPresenterIml(this, this)
            oldTenantPresenterIml.deleteParmanentData(tenantUtils)
        }

        builder.setNegativeButton(
            Config.NO_MESSAGE
        ){
                dialog: DialogInterface?, which: Int ->
        }

        val alertDialog = builder.create()
        alertDialog.show()

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

    override fun onResume() {
        super.onResume()
        tenantUtils = intent.getSerializableExtra(Config.PROPERTY_INFORMATION) as OldTenantUtils

        setViews()
        val particularPresenter = ParticularPresenterIml(
            this,
            this
        )
        particularPresenter.fetchData(tenantUtils.id!!)
    }

    override fun onPerticularFetchSuccess(particularList: List<ParticularUtils>) {
        super.onPerticularFetchSuccess(particularList)
        Log.d(TAG, "ParticularList: $particularList")

        val particularAdapter = ParticularListAdapter(
            this,
            particularList as ArrayList<ParticularUtils>,
            true
        )
        binding.list.adapter = particularAdapter
        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.setItemViewCacheSize(Config.LIST_CACHED_SIZE)
    }


    // Setting views
    private fun setViews() {
        binding.navigationTitle.text = tenantUtils.buildingName
        binding.currentTenantName.text = tenantUtils.tenantName
        binding.currentTenantPhone.text = tenantUtils.phoneNumber
        binding.totalDebit.text = tenantUtils.totalDebit.toString()
        binding.totalCredit.text = tenantUtils.totalCredit.toString()
        binding.idProof.text = tenantUtils.idProof

    }


    // Toolbar menu setting
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
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