package huuloc.uit.edu.viewmobeltest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import huuloc.uit.edu.viewmobeltest.Contact.Contact
import huuloc.uit.edu.viewmobeltest.Contact.ContactDAO

@Database(entities = [Contact::class], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contactDAO(): ContactDAO

    companion object {
        private var sInstance: AppDataBase? = null

        fun get(context: Context): AppDataBase? {
            if (sInstance == null) {
                synchronized(AppDataBase::class) {
                    sInstance = Room.databaseBuilder(context, AppDataBase::class.java, "contact_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return sInstance
        }
    }
}