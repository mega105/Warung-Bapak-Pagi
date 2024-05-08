package org.d3if0037.warungbapakpagi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0037.warungbapakpagi.ui.screen.AboutScreen
import org.d3if0037.warungbapakpagi.ui.screen.KEY_ID_ORDER
import org.d3if0037.warungbapakpagi.ui.screen.OrderScreen
import org.d3if0037.warungbapakpagi.ui.screen.KirimScreen
import org.d3if0037.warungbapakpagi.ui.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController)
        }

        // ORDER SCREEN START

        composable(route = Screen.OrderBaru.route) {
            OrderScreen(navController)
        }
        composable(
            route = Screen.OrderUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_ORDER) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_ORDER)
            OrderScreen(navController, id)
        }

        // ORDER SCREEN END

        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.Kirim.route) {
            KirimScreen(navController)
        }
    }
}