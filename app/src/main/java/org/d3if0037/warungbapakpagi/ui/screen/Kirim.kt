package org.d3if0037.warungbapakpagi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if0037.warungbapakpagi.R
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KirimScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.Test), // ganti string nya
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name)) // jangan lupa ganti (bukan nama aplikasi nya, tapi lagi di page apa)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
//        Text(
//            text = stringResource(id = R.string.Test),
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp))
        ScreenKirim(Modifier.padding(padding))
    }
}

@Composable
fun ScreenKirim(modifier: Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "ini deskripsi", // nanti ganti
            modifier = Modifier.size(250.dp)
        )
        Column (
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Disini Header (sebentar ganti)",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
            Text(
                text = "Kami sedang mengirim makanan ke anda, harap ditunggu(jangan lupa ganti)",
                color = Color.Gray,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Kembali ke menu awal (ganti)",
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KirimPreview() {
    WarungBapakPagiTheme {
        KirimScreen()
    }
}