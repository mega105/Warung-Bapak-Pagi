package org.d3if0037.warungbapakpagi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if0037.warungbapakpagi.database.model.Makanan
import org.d3if0037.warungbapakpagi.database.model.Order
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

@Composable
fun ListItem(order: Order, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        val namaMakananList = mutableListOf<String>()
        val namaMakananArray = order.nama.removeSurrounding("[", "]").split(", ").map { it.trim() }
        namaMakananArray.forEach { daftarMakanan -> namaMakananList.add(daftarMakanan)/*Text(text = "${daftarMakanan} to namaMakananList\n==============")*/ }
        namaMakananList.forEach { namaMakanan ->
            Makanan.values().forEach { enumMakanan ->
                if (namaMakanan == enumMakanan.nama) {
                    println("${enumMakanan.nama}: ${enumMakanan.harga}")
                    println("==============================================")
                    Row {
                        Text(
                            text = enumMakanan.nama,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = enumMakanan.harga.toString(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    //Text(text = "<=================>\n${enumMakanan.nama}: ${enumMakanan.harga}")
                }
            }
        }
        Text(
            text = "Catatan: \n ${order.catatan}",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

////        Makanan.values().forEach { enumMakanan ->
////            if (daftarMakanan.equals(enumMakanan.nama)) {
//                Text(
//                    text = daftarMakanan,
//                )
////                Text(
////                    text = enumMakanan.harga.toString(),
////                    maxLines = 1,
////                    overflow = TextOverflow.Ellipsis,
////                    fontWeight = FontWeight.Bold
////                )
//            }
//        }
////    }
////}
////    Column(
////        modifier = Modifier
////            .fillMaxWidth()
////            .clickable { onClick() }
////            .padding(16.dp),
////        verticalArrangement = Arrangement.spacedBy(8.dp)
////    )
////    {
////        Text(
////            text = order.nama,
////            maxLines = 1,
////            overflow = TextOverflow.Ellipsis,
////            fontWeight = FontWeight.Bold
////        )
////        Text(
////            text = order.harga,
////            maxLines = 1,
////            overflow = TextOverflow.Ellipsis,
////            fontWeight = FontWeight.Medium
////        )
////        Text(
////            text = order.catatan,
////            maxLines = 2,
////            overflow = TextOverflow.Ellipsis
////        )
//////        Text(text = "masukin tanggal gk ya?")
//    }


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ListPreview() {
//    WarungBapakPagiTheme {
//        ListItem {
//
//        }
//    }
//}