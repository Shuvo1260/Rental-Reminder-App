package org.binaryitplanet.rentalreminderapp.Features.View

import android.app.DatePickerDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddTenantBinding
import java.util.*

class AddTenant : AppCompatActivity(), TenantView {

    private val TAG = "AddTenant"

    private lateinit var binding: ActivityAddTenantBinding

    private var joinDay: Int = 0
    private var joinMonth: Int = 0
    private var joinYear: Int = 0

    private lateinit var joinDate: String
    private lateinit var tenantName: String
    private lateinit var phoneNumber: String
    private lateinit var idProof: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tenant)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_tenant)

        setUpToolbar()


        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.done) {
                saveData()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }

        getCurrentDate()

        setUpJoinedDate()

        setUpJoinDateListener()
    }

    private fun saveData() {

        if (checkValidity()) {
            Log.d(TAG, "Saving data")

            var property = intent.getSerializableExtra(Config.PROPERTY_INFORMATION) as PropertyUtils

            property.tenantName = tenantName
            property.phoneNumber = phoneNumber
            property.propertyStatus = true

            var tenantUtils = TenantUtils(
                null,
                property.id,
                tenantName,
                phoneNumber,
                joinDate,
                idProof,
                0,
                0
            )


            val tenantPresenterIml = TenantPresenterIml(this, this)
            tenantPresenterIml.saveData(tenantUtils, property)
        }
    }

    override fun onTenantAdd(status: Boolean) {
        super.onTenantAdd(status)
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


    private fun setUpJoinDateListener() {
        binding.joinDate.setOnClickListener {
            var datePickerDialog = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                joinDay = dayOfMonth
                joinMonth = month
                joinYear = year
                setUpJoinedDate()
            }, joinYear, joinMonth, joinDay)
            datePickerDialog.show()
        }
    }
    private fun setUpJoinedDate() {
        joinDate = "%02d/%02d/%04d".format(
            joinDay,
            joinMonth+1,
            joinYear
        )
        binding.joinDate.text = joinDate
    }

    private fun getCurrentDate() {
        val calendar = Calendar.getInstance()
        joinDay = calendar.get(Calendar.DAY_OF_MONTH)
        joinMonth = calendar.get(Calendar.MONTH)
        joinYear = calendar.get(Calendar.YEAR)
    }

    // Checking validity
    private fun checkValidity(): Boolean {
        tenantName = binding.tenantName.text.toString()
        phoneNumber = binding.phoneNumber.text.toString()
        idProof = binding.idProof.text.toString()

        if (tenantName.isNullOrEmpty()) {
            binding.tenantName.error = Config.ERROR_INVALID_MESSAGE
            binding.tenantName.requestFocus()
            return false
        }
        if (phoneNumber.isNullOrEmpty()) {
            binding.phoneNumber.error = Config.ERROR_INVALID_MESSAGE
            binding.phoneNumber.requestFocus()
            return false
        }

        if (idProof.isNullOrEmpty()) {
            binding.idProof.error = Config.ERROR_INVALID_MESSAGE
            binding.idProof.requestFocus()
            return false
        }

        return true
    }


    // Toolbar menu setting
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.done_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun setUpToolbar() {

        binding.toolbar.title = Config.TOOLBAR_TITLE_ADD_TENANT
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