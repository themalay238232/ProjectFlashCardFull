package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

/**
 * Một thẻ trong phiên ôn tập. Mặt trước là [tu], mặt sau gồm
 * [nghia], [phienAm] và [viDu].
 */
data class MucOnTap(
    val id: Long,
    val tu: String,
    val nghia: String,
    val phienAm: String,
    val viDu: String
)

data class OnTapFlashcardUiState(
    val tenBoThe: String = "",
    val dangTai: Boolean = true,
    val danhSachThe: List<MucOnTap> = emptyList(),
    val viTriHienTai: Int = 0,
    val daLatThe: Boolean = false,
    val dangXuLy: Boolean = false,
    val daHoanThanh: Boolean = false,
    // Lịch sử phiên ôn: số thẻ ở từng mức đánh giá.
    val soDaQuen: Int = 0,
    val soKhoNho: Int = 0,
    val soNhoDuoc: Int = 0,
    val soRatDe: Int = 0
) {
    val tongSoThe: Int get() = danhSachThe.size

    val theHienTai: MucOnTap? get() = danhSachThe.getOrNull(viTriHienTai)

    /** Tiến độ hiển thị dạng "1/10", "2/10"... */
    val tienDo: String
        get() = if (tongSoThe == 0) "0/0" else "${(viTriHienTai + 1).coerceAtMost(tongSoThe)}/$tongSoThe"

    val rong: Boolean get() = !dangTai && danhSachThe.isEmpty()
}