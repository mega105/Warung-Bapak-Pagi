package org.d3if0037.warungbapakpagi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if0037.warungbapakpagi.model.Menu

@Dao
interface MenuDao {

    @Insert
    suspend fun insert(menu: Menu)

    @Update
    suspend fun update(menu: Menu)

    @Query("SELECT * FROM menu ORDER BY nama DESC")
    fun getMenu(): Flow<List<Menu>>
}