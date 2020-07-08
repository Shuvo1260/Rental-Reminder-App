package org.binaryitplanet.rentalreminderapp.Features.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.binaryitplanet.rentalreminderapp.Utils.*

@Database(
    entities = [TenantUtils::class, ParticularUtils::class,
        PropertyUtils::class, OldTenantUtils::class],
    version = Config.DATABASE_VERSION
)

abstract class DatabaseManager: RoomDatabase() {

    abstract fun getTenantDAO() : TenantDAO
    abstract fun getParticularDAO(): ParticularDAO
    abstract fun getOldTenantDAO(): OldTenantDAO
    abstract fun getPropertyDAO(): PropertyDAO

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