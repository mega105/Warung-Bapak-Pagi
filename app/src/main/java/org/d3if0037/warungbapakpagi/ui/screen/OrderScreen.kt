package org.d3if0037.warungbapakpagi.ui.screen


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0037.warungbapakpagi.R
import org.d3if0037.warungbapakpagi.navigation.Screen
import org.d3if0037.warungbapakpagi.ui.theme.Purple80
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navHostController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        Content(Modifier.padding(padding), navHostController)
    }
}

@Composable
fun Content(modifier: Modifier, navController: NavHostController) {
    var catatan by rememberSaveable { mutableStateOf("") }
    val menu = Makanan.entries.toTypedArray()
    val pilihMenu =  rememberSaveable { mutableStateOf(mutableSetOf<Makanan>()) }

    var totalHarga by rememberSaveable { mutableStateOf(false) }
    var noItemSelected by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val pilihNamaMenu = pilihMenu.value.joinToString("\n") {
        "${it.nama} - Rp ${it.harga}"
    }
    val totalPrice = pilihMenu.value.sumOf { it.harga }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*==================================Card Start====================================*/

        Column {
            menu.forEach { makanan ->
                var isSelected by rememberSaveable { mutableStateOf(false) }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {

                            isSelected = !isSelected
                            if (isSelected) {
                                pilihMenu.value.add(makanan)
                            } else {
                                pilihMenu.value.remove(makanan)
                            }
                        }
                        .border(
                            2.dp,
                            if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    ),
                    shape = RoundedCornerShape(7.dp)
                ) {


                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Checkbox(
                                checked = pilihMenu.value.contains(makanan),
                                onCheckedChange = null,
                                colors = CheckboxDefaults.colors(Purple80),
                                modifier = Modifier
                                    .size(0.dp)
                                    .alpha(0f)
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            /*==================================Isi Card Start====================================*/

                            Image(
                                painter = painterResource(id = makanan.imageResId),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(75.dp)
                            )
                            Column(
                                modifier = Modifier.padding(horizontal = 12.dp)
                            ) {
                                Text(
                                    text = makanan.nama,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily.Monospace
                                )

                                Text(
                                    text = "Rp ${makanan.harga}",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                        }

                        /*==================================Isi Card End====================================*/
                    }



                }
            }

        }

        /*==================================Card End====================================*/

        /*==================================Catatan Start====================================*/

        OutlinedTextField(
            value = catatan,
            onValueChange = { catatan = it },
            label = { Text(text = stringResource(id = R.string.catatan)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        /*==================================Catatan End====================================*/

        /*==================================Button pesan Start====================================*/

        Button(
            onClick = {
                noItemSelected = pilihMenu.value.isEmpty()
                if (!noItemSelected) {
                    totalHarga = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(id = R.string.pesan))
        }

        /*==================================Button pesan End====================================*/

        /*==================================Logika Button pesan Start====================================*/

        /*==================================Tidak memilih Start====================================*/

        if (noItemSelected) {
            Text(
                text = stringResource(id = R.string.noItemSelected),
                color = Color.Red
            )
        }

        /*==================================Tidak memilih End====================================*/

        /*==================================Logika Button pesan Start====================================*/

        if (totalHarga) {
            Divider(
                modifier = Modifier.padding(top = 8.dp),
                thickness = 3.dp
            )

            /*==================================Logika Menu Start====================================*/

            pilihMenu.value.forEach { makanan ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = makanan.nama
                    )
                    Text(
                        text = "Rp ${makanan.harga}"
                    )
                }
            }

            /*==================================Logika Menu Start====================================*/

            Divider(
                modifier = Modifier,
                thickness = 1.dp
            )

            /*==================================Harga dan catatan Start====================================*/

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {

                val totalPriceMenu = pilihMenu.value.sumOf { it.harga }

                Text(
                    text = "Rp $totalPriceMenu",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = catatan,
                    color = Color.Gray
                )

            }

            /*==================================Harga dan catatan End====================================*/

            /*==================================Button Bagikan dan Kirim Start====================================*/

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(R.string.bagikan_template,
                            pilihNamaMenu,totalPrice
                                )
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = stringResource(id = R.string.bagikan))
                }
                Button(
                    onClick = { navController.navigate(Screen.Kirim.route) },
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.order))
                }
            }

            /*==================================Button Bagikan dan Kirim End====================================*/
        }

        /*==================================Logika Button pesan Start====================================*/
    }
}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ExperimentPreview() {
    WarungBapakPagiTheme {
        OrderScreen(rememberNavController())
    }
}