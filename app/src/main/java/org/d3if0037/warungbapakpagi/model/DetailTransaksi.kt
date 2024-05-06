package org.d3if0037.warungbapakpagi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailTransaksi")
data class DetailTransaksi(
    @PrimaryKey(autoGenerate = true)
    val id_p: Long = 0L,
    val catatan: String
)
