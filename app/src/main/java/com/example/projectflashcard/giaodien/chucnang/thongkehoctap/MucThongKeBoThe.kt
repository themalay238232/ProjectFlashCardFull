package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

/**
 * Thống kê tiến độ của một bộ thẻ cụ thể, dùng cho phần
 * "Thống kê theo từng bộ thẻ" trên màn hình Thống kê học tập.
 */
data class MucThongKeBoThe(
    val boTheId: Long,
    val tenBoThe: String,
    val tongSoTu: Int,
    val soTuDaThuoc: Int,
    val tyLeHoanThanh: Float
)