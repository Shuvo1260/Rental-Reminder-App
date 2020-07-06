package org.binaryitplanet.rentalreminderapp.Features.View

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.binaryitplanet.rentalreminderapp.Features.Presentar.ParticularPresenter
import org.binaryitplanet.rentalreminderapp.Features.Presentar.ParticularPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddParticularsBinding
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddPropertyBinding
import java.util.*

class AddParticulars : AppCompatActivity(), ParticularView {


    private val TAG = "AddParticular"

    private lateinit var binding: ActivityAddParticularsBinding

    private var month: String? = null
    private var year: String? = null
    private var transactionType: String? = null
    private var amount: String? = null
    private var remark: String? = null

    private lateinit var tenantUtils: TenantUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_particulars)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_particulars)

        setUpToolbar()

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.done) {
                saveData()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }

        setUpDropDown()
    }

    private fun setUpDropDown() {
        var transactionTypes = resources.getStringArray(R.array.transactionType)
        var months = resources.getStringArray(R.array.months)
        var years = resources.getStringArray(R.array.years)

        var transactionAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            transactionTypes
        )
        var monthsAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            months
        )
        var yearsAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            years
        )

        binding.type.setText(transactionTypes[0])

        binding.type.setAdapter(transactionAdapter)
        binding.month.setAdapter(monthsAdapter)
        binding.year.setAdapter(yearsAdapter)
    }

    private fun saveData() {
        Log.d(TAG, "Saving Data")
        if(checkValidity()){
            tenantUtils = intent.getSerializableExtra(Config.PROPERTY_INFORMATION) as TenantUtils
            val date = getCurrentDate()
            val particularUtils = ParticularUtils(
                null,
                tenantUtils.id!!,
                transactionType!!,
                amount = amount?.toInt()!!,
                date = date,
                month = month!!,
                year = year?.toInt()!!,
                remark = remark!!
            )
            val particularPresenter = ParticularPresenterIml(
                this,
                this
            )
            particularPresenter.saveData(
                tenantUtils,
                particularUtils
            )
        }
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val date = calendar.get(Calendar.DAY_OF_MONTH).toString() + "/" +
                (calendar.get(Calendar.MONTH)+1) + "/" +
                calendar.get(Calendar.YEAR)

        Log.d(TAG, "Date: $date")
        return date
    }

    override fun onPerticularAdd(status: Boolean) {
        super.onPerticularAdd(status)
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
                Config.SUCCESS_MESSAGE,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Checking validity
    private fun checkValidity(): Boolean {
        transactionType = binding.type.text.toString()
        amount = binding.amount.text.toString()
        month = binding.month.text.toString()
        year = binding.year.text.toString()
        remark = binding.remark.text.toString()

        if (transactionType.isNullOrEmpty()) {
            binding.type.error = Config.ERROR_INVALID_MESSAGE
            binding.type.requestFocus()
            return false
        }
        if (amount.isNullOrEmpty()) {
            binding.amount.error = Config.ERROR_INVALID_MESSAGE
            binding.amount.requestFocus()
            return false
        }
        if (month.isNullOrEmpty()) {
            binding.month.error = Config.ERROR_INVALID_MESSAGE
            binding.month.requestFocus()
            return false
        }
        if (year.isNullOrEmpty()) {
            binding.year.error = Config.ERROR_INVALID_MESSAGE
            binding.year.requestFocus()
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

        binding.toolbar.title = Config.TOOLBAR_TITLE_ADD_PARTICULAR
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