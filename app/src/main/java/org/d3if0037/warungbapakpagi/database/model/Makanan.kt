package org.d3if0037.warungbapakpagi.database.model

import androidx.annotation.DrawableRes
import org.d3if0037.warungbapakpagi.R

enum class Makanan(
    val nama: String,
    @DrawableRes val imageResId: Int,
    val harga: Int
) {
    NASI("Nasi", R.drawable.nasi_putih, 2000),
    TEMPE_OREK("Tempe Orek", R.drawable.tempe_orek, 3000),
    SOUP_AYAM("Soup Ayam", R.drawable.sop_ayam_kampung, 4000),
    ES_TEH("Es Teh", R.drawable.es_teh, 4000)
}