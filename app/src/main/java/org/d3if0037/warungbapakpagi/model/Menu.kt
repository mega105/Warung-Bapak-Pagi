package org.d3if0037.warungbapakpagi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menu")
data class Menu(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val nama: String,
    val harga: String,
)
