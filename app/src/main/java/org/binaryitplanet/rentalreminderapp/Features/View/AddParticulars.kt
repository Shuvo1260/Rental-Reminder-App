package org.binaryitplanet.rentalreminderapp.Features.View

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddParticularsBinding
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddPropertyBinding

class AddParticulars : AppCompatActivity() {


    private val TAG = "AddPerticular"

    private lateinit var binding: ActivityAddParticularsBinding

    private var month: String? = null
    private var year: String? = null
    private var transactionType: String? = null
    private var amount: String? = null

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
//            val particularUtils = ParticularUtils(
//                null,
//
//            )
            onBackPressed()
        }
    }

    private fun checkValidity(): Boolean {
        transactionType = binding.type.text.toString()
        amount = binding.amount.text.toString()
        month = binding.month.text.toString()
        year = binding.year.text.toString()

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