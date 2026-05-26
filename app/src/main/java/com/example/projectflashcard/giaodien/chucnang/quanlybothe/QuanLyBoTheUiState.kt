package com.example.projectflashcard.giaodien.chucnang.quanlybothe

import com.example.projectflashcard.nghiepvu.kieudulieu.BoThe

data class QuanLyBoTheUiState(
    val danhSach: List<BoThe> = emptyList(),
    val tuKhoaTimKiem: String = "",
    val dangTai: Boolean = true,
    val thongBaoLoi: String? = null,
    val hienThiHopThoai: Boolean = false,
    val boTheChinhSua: BoThe? = null,
    val hienThiXacNhanXoa: Boolean = false,
    val boTheSeXoa: BoThe? = null
)
