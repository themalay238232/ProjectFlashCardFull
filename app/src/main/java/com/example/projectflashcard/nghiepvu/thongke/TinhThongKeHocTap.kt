package com.example.projectflashcard.nghiepvu.thongke

import com.example.projectflashcard.nghiepvu.kieudulieu.LichSuOnTap
import com.example.projectflashcard.nghiepvu.kieudulieu.TrangThaiTuVung
import com.example.projectflashcard.nghiepvu.kieudulieu.TuVung

data class KetQuaThongKe(
    val tongSoTu: Int = 0,
    val soTuDaThuoc: Int = 0,
    val soTuChuaThuoc: Int = 0,
    val soTuCanOnHomNay: Int = 0,
    val tongLuotOn: Int = 0,
    val tyLeHoanThanh: Int = 0
)

object TinhThongKeHocTap {
    fun tinh(
        danhSachTu: List<TuVung>,
        lichSuOnTap: List<LichSuOnTap>
    ): KetQuaThongKe {
        val tongSoTu = danhSachTu.size
        val soTuDaThuoc = danhSachTu.count { it.trangThai == TrangThaiTuVung.DA_THUOC }
        val soTuChuaThuoc = tongSoTu - soTuDaThuoc
        val soTuCanOnHomNay = danhSachTu.count {
            it.trangThai != TrangThaiTuVung.DA_THUOC && it.canOnHomNay
        }
        val tongLuotOn = lichSuOnTap.size
        val tyLeHoanThanh = if (tongSoTu == 0) 0 else soTuDaThuoc * 100 / tongSoTu

        return KetQuaThongKe(
            tongSoTu = tongSoTu,
            soTuDaThuoc = soTuDaThuoc,
            soTuChuaThuoc = soTuChuaThuoc,
            soTuCanOnHomNay = soTuCanOnHomNay,
            tongLuotOn = tongLuotOn,
            tyLeHoanThanh = tyLeHoanThanh
        )
    }
}