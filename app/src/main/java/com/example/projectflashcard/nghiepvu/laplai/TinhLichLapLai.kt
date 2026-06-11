package com.example.projectflashcard.nghiepvu.laplai

import com.example.projectflashcard.nghiepvu.kieudulieu.MucDoOnTap
import com.example.projectflashcard.nghiepvu.kieudulieu.TrangThaiTuVung

object TinhLichLapLai {
    const val NGUONG_DA_THUOC = 3
    const val MOT_NGAY_MILLIS = 24L * 60 * 60 * 1000

    fun tinh(
        soLanNhoTotHienTai: Int,
        mucDoOnTap: MucDoOnTap,
        thoiDiemOn: Long = System.currentTimeMillis()
    ): KetQuaOnTap {
        val soLanNhoTotMoi = when (mucDoOnTap) {
            MucDoOnTap.DA_QUEN -> 0
            MucDoOnTap.KHO_NHO -> soLanNhoTotHienTai
            MucDoOnTap.NHO_DUOC -> soLanNhoTotHienTai + 1
            MucDoOnTap.RAT_DE -> soLanNhoTotHienTai + 1
        }

        val khoangCachNgay = when (mucDoOnTap) {
            MucDoOnTap.DA_QUEN -> 0
            MucDoOnTap.KHO_NHO -> 1
            MucDoOnTap.NHO_DUOC -> soLanNhoTotMoi * 2
            MucDoOnTap.RAT_DE -> soLanNhoTotMoi * 4
        }

        val daThuoc = when (mucDoOnTap) {
            MucDoOnTap.NHO_DUOC, MucDoOnTap.RAT_DE -> soLanNhoTotMoi >= NGUONG_DA_THUOC
            else -> false
        }

        val trangThaiMoi = if (daThuoc) {
            TrangThaiTuVung.DA_THUOC
        } else {
            TrangThaiTuVung.DANG_HOC
        }

        val ngayOnTiepTheo = thoiDiemOn + khoangCachNgay * MOT_NGAY_MILLIS

        return KetQuaOnTap(
            trangThaiMoi = trangThaiMoi,
            soLanNhoTotMoi = soLanNhoTotMoi,
            khoangCachNgay = khoangCachNgay,
            ngayOnTiepTheo = ngayOnTiepTheo
        )
    }
}