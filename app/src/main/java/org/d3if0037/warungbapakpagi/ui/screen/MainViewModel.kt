package org.d3if0037.warungbapakpagi.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.d3if0037.warungbapakpagi.database.OrderDao
import org.d3if0037.warungbapakpagi.model.Order

class MainViewModel(val dao: OrderDao) : ViewModel() {

    val data: StateFlow<List<Order>> = dao.getOrder().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    suspend fun getOrderById(id: Long): Order? {
        return dao.getOrderById(id)
    }

    fun insert(nama: String, harga: String, catatan: String) {
        val order = Order (
            nama = nama,
            harga = harga,
            catatan = catatan
        )
        viewModelScope.launch {
            dao.insert(order)
        }
    }

    fun update(id: Long, nama: String, harga: String, catatan: String){
        val order = Order (
            id = id,
            nama = nama,
            harga = harga,
            catatan = catatan
        )
        viewModelScope.launch {
            dao.update(order)
        }
    }

    fun deleteOrderById(id: Long){
        viewModelScope.launch {
            dao.deleteOrderById(id)
        }
    }
}