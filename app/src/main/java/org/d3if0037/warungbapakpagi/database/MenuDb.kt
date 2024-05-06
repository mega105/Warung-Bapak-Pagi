package org.d3if0037.mobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if0037.warungbapakpagi.database.MenuDao
import org.d3if0037.warungbapakpagi.model.Menu

@Database(entities = [Menu::class], version = 1, exportSchema = false)
abstract class MenuDb : RoomDatabase() {

    abstract val dao: MenuDao

    companion object {

        @Volatile
        private var INSTANCE: MenuDb? = null

        fun getInstance(context: Context): MenuDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MenuDb::class.java,
                        "catatan.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}