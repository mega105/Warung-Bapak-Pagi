package org.d3if0037.warungbapakpagi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0037.warungbapakpagi.ui.screen.AboutScreen
import org.d3if0037.warungbapakpagi.ui.screen.MainScreen
import org.d3if0037.warungbapakpagi.ui.screen.KirimScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Kirim.route) {
            KirimScreen(navController)
        }
    }
}