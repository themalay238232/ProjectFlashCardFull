package com.example.projectflashcard.nghiepvu.laplai

import com.example.projectflashcard.nghiepvu.kieudulieu.TrangThaiTuVung

data class KetQuaOnTap(
    val trangThaiMoi: TrangThaiTuVung,
    val soLanNhoTotMoi: Int,
    val khoangCachNgay: Int,
    val ngayOnTiepTheo: Long
)