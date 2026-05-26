package com.example.projectflashcard.giaodien.chucnang.themsuatuvung

import com.example.projectflashcard.nghiepvu.kieudulieu.TuVung

data class ThemSuaTuVungUiState(
    val boTheId: Int = 1,
    val tenBoThe: String = "",
    val tuVungId: Long? = null,
    val tuVungGoc: TuVung? = null,
    val tu: String = "",
    val nghia: String = "",
    val phienAm: String = "",
    val viDu: String = "",
    val thongBaoLoi: String? = null,
    val dangTai: Boolean = false,
    val dangLuu: Boolean = false,
    val daLuu: Boolean = false
)
