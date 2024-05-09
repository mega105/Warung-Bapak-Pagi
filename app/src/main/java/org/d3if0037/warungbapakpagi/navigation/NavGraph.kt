package org.d3if0037.warungbapakpagi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0037.warungbapakpagi.database.OrderDb
import org.d3if0037.warungbapakpagi.ui.screen.AboutScreen
import org.d3if0037.warungbapakpagi.ui.screen.KEY_ID_ORDER
import org.d3if0037.warungbapakpagi.ui.screen.OrderScreen
import org.d3if0037.warungbapakpagi.ui.screen.KirimScreen
import org.d3if0037.warungbapakpagi.ui.screen.MainScreen
import org.d3if0037.warungbapakpagi.MainViewModel
import org.d3if0037.warungbapakpagi.util.ViewModelFactory

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {


    val context = LocalContext.current
    val db = OrderDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            MainScreen(navController, viewModel)
        }

        // ORDER SCREEN START

        composable(route = Screen.OrderBaru.route) {
            OrderScreen(viewModel, navController)
        }
        composable(
            route = Screen.OrderUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_ORDER) { type = NavType.LongType }
            )
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_ORDER)
            OrderScreen(viewModel, navController, id)
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