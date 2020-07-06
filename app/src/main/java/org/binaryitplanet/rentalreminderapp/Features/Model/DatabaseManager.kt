package org.binaryitplanet.rentalreminderapp.Features.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.binaryitplanet.rentalreminderapp.Utils.Config
import org.binaryitplanet.rentalreminderapp.Utils.ParticularUtils
import org.binaryitplanet.rentalreminderapp.Utils.ReminderUtils
import org.binaryitplanet.rentalreminderapp.Utils.TenantUtils

@Database(
    entities = [TenantUtils::class, ReminderUtils::class, ParticularUtils::class],
    version = Config.DATABASE_VERSION
)

abstract class DatabaseManager: RoomDatabase() {

    abstract fun getTenantDAO() : TenantDAO
    abstract fun getReminderDAO(): ReminderDAO
    abstract fun getParticularDAO(): ParticularDAO

    companion object{
        var INSTANCE: DatabaseManager? = null

        fun getInstance(context: Context): DatabaseManager? {

            if (INSTANCE == null) {
                synchronized(DatabaseManager::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseManager::class.java,
                        Config.DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }

            return INSTANCE
        }

        fun destroy(){
            INSTANCE = null
        }
    }
}