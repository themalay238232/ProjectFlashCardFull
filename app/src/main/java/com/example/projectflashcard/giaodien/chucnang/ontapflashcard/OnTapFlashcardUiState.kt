package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import com.example.projectflashcard.nghiepvu.kieudulieu.MucDoOnTap

data class MucOnTap(
    val id: Long,
    val tu: String,
    val nghia: String,
    val phienAm: String,
    val viDu: String
)

data class OnTapFlashcardUiState(
    val tenPhienOnTap: String = "On tap Flashcard",
    val dangTai: Boolean = true,
    val danhSachThe: List<MucOnTap> = emptyList(),
    val viTriHienTai: Int = 0,
    val daLatThe: Boolean = false,
    val dangXuLy: Boolean = false,
    val daHoanThanh: Boolean = false,
    val thongBaoLoi: String? = null,
    val soDaQuen: Int = 0,
    val soKhoNho: Int = 0,
    val soNhoDuoc: Int = 0,
    val soRatDe: Int = 0
) {
    val tongSoThe: Int get() = danhSachThe.size
    val theHienTai: MucOnTap? get() = danhSachThe.getOrNull(viTriHienTai)
    val rong: Boolean get() = !dangTai && danhSachThe.isEmpty()
    val tienDo: String
        get() = if (tongSoThe == 0) "0/0" else "${(viTriHienTai + 1).coerceAtMost(tongSoThe)}/$tongSoThe"
}

fun MucDoOnTap.nhanHienThi(): String = when (this) {
    MucDoOnTap.DA_QUEN -> "Da quen"
    MucDoOnTap.KHO_NHO -> "Kho nho"
    MucDoOnTap.NHO_DUOC -> "Nho duoc"
    MucDoOnTap.RAT_DE -> "Rat de"
}
