package org.d3if0037.warungbapakpagi.ui.screen


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0037.warungbapakpagi.MainViewModel
import org.d3if0037.warungbapakpagi.R
import org.d3if0037.warungbapakpagi.database.model.Makanan
import org.d3if0037.warungbapakpagi.navigation.Screen
import org.d3if0037.warungbapakpagi.ui.component.shareData
import org.d3if0037.warungbapakpagi.ui.theme.Purple80
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

const val KEY_ID_ORDER = "idOrder"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(viewModel: MainViewModel, navHostController: NavHostController, id: Long? = null) {
    val context = LocalContext.current

    var namaOrder by remember { mutableStateOf("") }
    var hargaOrder by remember { mutableStateOf("") }
    var catatanOrder by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getOrderById(id) ?: return@LaunchedEffect
        namaOrder = data.nama
        hargaOrder = data.harga
        catatanOrder = data.catatan
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_pesanan))
                    else
                        Text(text = stringResource(id = R.string.ubah_pesanan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
//                    IconButton(onClick = {
//                        if (namaOrder == "" || hargaOrder == "" || catatanOrder == "") {
//                            Toast.makeText(context, R.string.empty_id, Toast.LENGTH_LONG).show()
//                            return@IconButton
//                        }
//                        if (id == null) {
//                            viewModel.insert(namaOrder, hargaOrder, catatanOrder)
//                        } else {
//                            viewModel.update(id, namaOrder, hargaOrder, catatanOrder)
//                        }
//                        navHostController.popBackStack()
//                    }) {
//                        Icon(
//                            imageVector = Icons.Outlined.Check,
//                            contentDescription = stringResource(id = R.string.Simpan),
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }

                    if (id != null) {
                        DeleteAction { showDialog = true }
                        DisplayAlertDialog(
                            openDialog = showDialog,
                            onDismissRequest = { showDialog = false }) {
                            showDialog = false
                            viewModel.deleteOrderById(id)
                            navHostController.popBackStack()
                        }
                    }
                }
            )
        }
    ) { padding ->
        Content(
            nama = namaOrder,
            onTitleChange = { namaOrder = it },
            harga = hargaOrder,
            onHargaChange = { hargaOrder = it },
            catatan = catatanOrder,
            onCatatanChange = { catatanOrder = it },
            viewModel,
            Modifier.padding(padding), navHostController, id
        )
    }
}

@Composable
fun Content(
    nama: String, onTitleChange: (String) -> Unit,
    harga: String, onHargaChange: (String) -> Unit,
    catatan: String, onCatatanChange: (String) -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier, navController: NavHostController, id: Long? = null
) {
    val menu = Makanan.entries.toTypedArray()
    val pilihMenu = rememberSaveable { mutableStateOf(mutableSetOf<Makanan>()) }


    var isSelected by rememberSaveable { mutableStateOf(false) }

/////////////////////////////////checkbox////////////////////////
    LaunchedEffect(true) {
        if (id == null) return@LaunchedEffect
        val namaMakananList = mutableListOf<String>()
        val data = viewModel.getOrderById(id) ?: return@LaunchedEffect
        val namaMakananArray = data.nama.removeSurrounding("[", "]").split(", ").map { it.trim() }
        namaMakananArray.forEach { daftarMakanan ->
            namaMakananList.add(daftarMakanan)
            namaMakananList.forEach { namaMakanan ->
                Makanan.values().forEach { enumMakanan ->
                    if (namaMakanan == enumMakanan.nama) {
                        pilihMenu.value.add(enumMakanan)
                    }
                }
            }
        }
    }

    var totalHarga by rememberSaveable { mutableStateOf(false) }
    var noItemSelected by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    val pilihNamaMenu = pilihMenu.value.joinToString("\n") {
        "${it.nama} - Rp ${it.harga}"
    }
    val totalPrice = pilihMenu.value.sumOf { it.harga }

    Column(
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
                            if (isSelected || pilihMenu.value.contains(makanan))
                                MaterialTheme.colorScheme.primary else Color.Transparent,
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
            onValueChange = { onCatatanChange(it) },
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
            Toast.makeText(context, R.string.empty_id, Toast.LENGTH_LONG).show()
        }

        /*==================================Tidak memilih End====================================*/

        /*==================================Logika Button pesan Start====================================*/

        if (totalHarga) {
            Divider(
                modifier = Modifier.padding(top = 8.dp),
                thickness = 3.dp
            )

            /*==================================Logika Menu Start====================================*/
            //ini loop detail pembelian
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

                //total harga
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
                            message = context.getString(
                                R.string.bagikan_template,
                                pilihNamaMenu, totalPrice
                            )
                        )
                    },
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = stringResource(id = R.string.bagikan))
                }
                Button(
                    onClick = {
                        println("==============================================")
                        val namaMakananList = mutableListOf<String>()
                        val hargaMakananList = mutableListOf<String>()
                        pilihMenu.value.forEach { makanan ->
                            println(makanan.nama)
                            Makanan.values().forEach { enumMakanan ->
                                if (makanan.nama == enumMakanan.nama) {
                                    println(enumMakanan.harga)
                                    namaMakananList.add(enumMakanan.nama)
                                    hargaMakananList.add(enumMakanan.harga.toString())
                                }
                            }
                        }
                        println("List nama makanan : $namaMakananList")

//                        viewModel.insert(
//                            namaMakananList.toString(),
//                            hargaMakananList.toString(),
//                            catatan
//                        )
                        if (id == null) {
                            viewModel.insert(
                                namaMakananList.toString(),
                                hargaMakananList.toString(),
                                catatan
                            )
                        } else {
                            viewModel.update(
                                id, namaMakananList.toString(),
                                hargaMakananList.toString(),
                                catatan
                            )
                        }

                        namaMakananList.forEach { arrayMakanan ->
                            Makanan.values().forEach { enumMakanan ->
                                if (arrayMakanan == enumMakanan.nama) {
                                    println("${enumMakanan.nama}: ${enumMakanan.harga}")
                                    println("==============================================")
                                }
                            }
                        }
                        navController.navigate(Screen.Kirim.route)
                    },
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

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ExperimentPreview() {
    WarungBapakPagiTheme {
        val viewModel: MainViewModel = viewModel()
        OrderScreen(viewModel, rememberNavController())
    }
}