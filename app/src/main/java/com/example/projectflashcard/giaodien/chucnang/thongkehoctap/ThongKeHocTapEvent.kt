package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

sealed interface ThongKeHocTapEvent {

    // --- Dữ liệu ---
    data object TaiThongKe : ThongKeHocTapEvent

    // --- Điều hướng (xử lý ở NavGraph, không qua ViewModel) ---
    data object QuayLai : ThongKeHocTapEvent
}