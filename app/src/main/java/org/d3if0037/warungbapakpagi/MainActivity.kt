package org.d3if0037.warungbapakpagi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.d3if0037.warungbapakpagi.navigation.SetupNavGraph
import org.d3if0037.warungbapakpagi.ui.screen.MainScreen
//import org.d3if0037.warungbapakpagi.ui.screen.AboutScreen
import org.d3if0037.warungbapakpagi.ui.theme.WarungBapakPagiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WarungBapakPagiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    MainScreen(navController = rememberNavController())
                    SetupNavGraph()
                }
            }
        }
    }
}
