package org.d3if0037.warungbapakpagi.navigation

import org.d3if0037.warungbapakpagi.ui.screen.KEY_ID_ORDER

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object OrderBaru: Screen("orderScreen")
    data object OrderUbah: Screen("orderScreen/{$KEY_ID_ORDER}") {
        fun withId(id: Long) = "orderScreen/$id"
    }
    data object About: Screen("aboutScreen")
    data object Kirim: Screen("kirimScreen")
}