package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

data class ThongKeHocTapUiState(
    val dangTai: Boolean = true,
    val tongSoBoThe: Int = 0,
    val tongSoTuVung: Int = 0,
    val soTuDaThuoc: Int = 0,
    val soTuChuaThuoc: Int = 0,
    val soTuCanOnHomNay: Int = 0,
    val tongLuotOnTap: Int = 0,
    val tyLeHoanThanh: Float = 0f,
    val thongKeTheoBoThe: List<MucThongKeBoThe> = emptyList()
) {
    /** Tỷ lệ hoàn thành dạng phần trăm (0..100), đã làm tròn. */
    val phanTramHoanThanh: Int
        get() = (tyLeHoanThanh * 100).toInt()
}