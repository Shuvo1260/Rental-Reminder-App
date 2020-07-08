package org.binaryitplanet.rentalreminderapp.Features.View

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_add_tenant.*
import org.binaryitplanet.rentalreminderapp.Features.Presentar.PropertyPresentar
import org.binaryitplanet.rentalreminderapp.Features.Presentar.PropertyPresenterIml
import org.binaryitplanet.rentalreminderapp.Features.Presentar.TenantPresenterIml
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.PropertyUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils
import org.binaryitplanet.rentalreminderapp.databinding.ActivityAddPropertyBinding

class AddProperty : AppCompatActivity(), PropertyView {

    private val TAG = "AddProperty"

    private lateinit var binding: ActivityAddPropertyBinding

    private lateinit var buildingName: String
    private lateinit var address: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_property)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)

        setUpToolbar()

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.done) {
                if(checkValidity())
                    saveData()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }
    }

    // Checking input field validity
    private fun checkValidity(): Boolean {
        buildingName = binding.buildingName.text.toString()
        address = binding.address.text.toString()

        if(buildingName.isNullOrEmpty()) {
            binding.buildingName.error = Config.ERROR_INVALID_MESSAGE
            binding.buildingName.requestFocus()
            return false
        }
        if(address.isNullOrEmpty()) {
            binding.address.error = Config.ERROR_INVALID_MESSAGE
            binding.address.requestFocus()
            return false
        }
        return true
    }

    // Saving data
    private fun saveData() {
        Log.d(TAG, "Saving Data: $buildingName")

        val propertyUtils = PropertyUtils(
            null,
            buildingName,
            null,
            null,
            false,
            null,
            address,
            null
        )

        val presenter = PropertyPresenterIml(this, this)
        presenter.saveData(propertyUtils)
    }

    // Saving status
    override fun onPropertyAdd(status: Boolean) {
        super.onPropertyAdd(status)
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
        menuInflater.inflate(R.menu.done_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun setUpToolbar() {

        binding.toolbar.title = Config.TOOLBAR_TITLE_ADD_PROPERTY
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