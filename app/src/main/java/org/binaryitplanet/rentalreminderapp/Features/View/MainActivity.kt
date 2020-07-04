package org.binaryitplanet.rentalreminderapp.Features.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.navigation.NavigationView
import org.binaryitplanet.rentalreminderapp.R
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var binding: ActivityMainBinding

    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        setUpToolbarTitle(Config.TOOLBAR_TITLE_HOME)

        setUpDrawerToggle()

        binding.navigation.setNavigationItemSelectedListener(this)

    }

    // Setting up the drawertoggle or menu toggle
    private fun setUpDrawerToggle() {
        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open,
            R.string.close
        )
        drawerToggle!!.syncState()
    }

    // Setting navigation title
    private fun setUpToolbarTitle(title: String) {
        binding.navigationTitle.text = title
    }


    // Navigation item selection listener
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_home) {
            setUpToolbarTitle(Config.TOOLBAR_TITLE_HOME)
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
        }else if (item.itemId == R.id.nav_old_tenant) {
            setUpToolbarTitle(Config.TOOLBAR_TITLE_OLD_TENANT)
            Toast.makeText(this, "Old tenant", Toast.LENGTH_SHORT).show()
        }else if (item.itemId == R.id.nav_backup_and_restore) {
            setUpToolbarTitle(Config.TOOLBAR_TITLE_BACKUP_AND_RESTORE)
            Toast.makeText(this, "Backup and restore", Toast.LENGTH_SHORT).show()
        }else if (item.itemId == R.id.nav_exit) {
            Toast.makeText(this, "Exit", Toast.LENGTH_SHORT).show()
            overridePendingTransition(R.anim.positiontotop, R.anim.toptobottom)
            finish()
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(binding.navigation)){
            binding.drawerLayout.closeDrawer(binding.navigation);
        }else {
            super.onBackPressed()
        }
    }
}