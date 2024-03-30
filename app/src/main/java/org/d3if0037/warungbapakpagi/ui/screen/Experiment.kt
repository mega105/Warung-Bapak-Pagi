package org.d3if0037.warungbapakpagi.ui.screen

import android.content.res.Configuration
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if0037.warungbapakpagi.R
import org.d3if0037.warungbapakpagi.ui.theme.Purple80
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Experiment() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Content(Modifier.padding(padding))
    }
}

/*--------------------[ Inner Content ]--------------------*/
@Composable
fun Content(modifier: Modifier) {
    var catatan by rememberSaveable { mutableStateOf("") }

    /*--------------------[ Container ]--------------------*/
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isChecked by rememberSaveable { mutableStateOf(false) } // Buat mutable state untuk status checkbox
        var isSelected by rememberSaveable { mutableStateOf(false) } // Buat mutable state untuk status seleksi kartu

        /*--------------------[ Menu Grid ]--------------------*/
        Card(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(10.dp)
                .clickable { // Tambahkan clickable modifier pada Card
                    isSelected = !isSelected // Toggle status seleksi ketika Card diklik
                    isChecked =
                        isSelected // Set status checkbox sesuai dengan status seleksi
                }
                .border(
                    2.dp,
                    if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                ), // Tambahkan border dengan kondisional berdasarkan status seleksi
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
            shape = RoundedCornerShape(7.dp)
        ) {
            /*--------------------[ 1 Scope ]--------------------*/
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Checkbox(
                        checked = isChecked, // Gunakan status yang diperbarui
                        onCheckedChange = {
                            isChecked = it
                        }, // Perbarui status ketika checkbox diubah
                        colors = CheckboxDefaults.colors(Purple80),
                        modifier = Modifier
                            .size(0.dp)
                            .alpha(0f)
                    )
                }


                Row(
                    modifier = Modifier
                        .fillMaxSize(),
//                        .padding(bottom = 10.dp), // padding na
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background), // ganti dengan gambar Anda
                        contentDescription = "ini deskripsi", // ganti juga
                        modifier = Modifier.size(75.dp)
                    )
                    Column (
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = "Name 1",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )

                        Text(
                            text = "Department 1",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }


                }


            }
            /*--------------------[ 1 Scope ]--------------------*/





        /*--------------------[ Menu Grid End ]--------------------*/
        }


        /*--------------------[ Order Notes ]--------------------*/
        OutlinedTextField(
            value = catatan,
            onValueChange = { catatan = it },
            modifier = Modifier.fillMaxWidth()
        )
        /*--------------------[ Order Notes End ]--------------------*/

        /*--------------------[ Confirm Button ]--------------------*/
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "test") // jangan lupa ganti
        }
        /*--------------------[ Confirm Button end]--------------------*/

        Divider(
            modifier = Modifier.padding(top = 8.dp),
            thickness = 3.dp
        )

        /*--------------------[ Order List ]--------------------*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Nilai"
            )
            Text(
                text = "2000"
            )
        }
        /*--------------------[ Order List End]--------------------*/

        Divider(
            modifier = Modifier,
            thickness = 1.dp
        )

        /*--------------------[ Total Price ]--------------------*/
        Text(
            text = "2000",
            style = MaterialTheme.typography.titleLarge
        )
        /*--------------------[ Total Price ]--------------------*/

        /*--------------------[ Share & Confirm Order ]--------------------*/
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(horizontal = 32.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Bagikan")
            }
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(horizontal = 32.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Kirim")
            }
        }
        /*--------------------[ Share & Confirm Order ]--------------------*/

    }
}

fun MenuContainer(){}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ExperimentPreview() {
    WarungBapakPagiTheme {
        Experiment()
    }
}