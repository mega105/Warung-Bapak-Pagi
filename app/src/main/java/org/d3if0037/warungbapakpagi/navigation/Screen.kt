package org.d3if0037.warungbapakpagi.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Kirim: Screen("kirimScreen")
}