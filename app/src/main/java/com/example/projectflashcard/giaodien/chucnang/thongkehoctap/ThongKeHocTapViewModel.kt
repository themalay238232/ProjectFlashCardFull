package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectflashcard.dulieu.cucbo.cosodulieu.CoSoDuLieuLearnFlash
import com.example.projectflashcard.dulieu.khodulieu.KhoDuLieuFlashcard
import com.example.projectflashcard.nghiepvu.kieudulieu.BoThe
import com.example.projectflashcard.nghiepvu.kieudulieu.TrangThaiTuVung
import com.example.projectflashcard.nghiepvu.kieudulieu.TuVung
import com.example.projectflashcard.nghiepvu.khodulieu.KhoFlashcard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ThongKeHocTapViewModel(application: Application) : AndroidViewModel(application) {

    private val kho: KhoFlashcard
    private val _uiState = MutableStateFlow(ThongKeHocTapUiState())
    val uiState: StateFlow<ThongKeHocTapUiState> = _uiState.asStateFlow()

    init {
        val db = CoSoDuLieuLearnFlash.layInstance(application)
        kho = KhoDuLieuFlashcard(db.truyVanBoThe(), db.truyVanTuVung())

        viewModelScope.launch {
            combine(
                kho.layTatCaBoThe(),
                kho.layTatCaTuVung()
            ) { danhSachBoThe, danhSachTuVung ->
                tinhThongKe(danhSachBoThe, danhSachTuVung)
            }.collect { thongKe ->
                _uiState.value = thongKe
            }
        }
    }

    fun xuLySuKien(suKien: ThongKeHocTapEvent) {
        when (suKien) {
            // Dữ liệu tự cập nhật qua Flow nên không cần tải lại thủ công.
            ThongKeHocTapEvent.TaiThongKe,
            ThongKeHocTapEvent.QuayLai -> Unit
        }
    }

    private fun tinhThongKe(
        danhSachBoThe: List<BoThe>,
        danhSachTuVung: List<TuVung>
    ): ThongKeHocTapUiState {
        val tongSoTuVung = danhSachTuVung.size
        val soTuDaThuoc = danhSachTuVung.count { it.trangThai == TrangThaiTuVung.DA_THUOC }
        val soTuChuaThuoc = tongSoTuVung - soTuDaThuoc
        val soTuCanOnHomNay = danhSachTuVung.count {
            it.trangThai != TrangThaiTuVung.DA_THUOC && it.canOnHomNay
        }

        // Tránh chia cho 0 khi chưa có từ vựng nào.
        val tyLeHoanThanh =
            if (tongSoTuVung == 0) 0f
            else soTuDaThuoc.toFloat() / tongSoTuVung

        val tuVungTheoBoThe = danhSachTuVung.groupBy { it.boTheId }
        val thongKeTheoBoThe = danhSachBoThe.map { boThe ->
            val tuVungCuaBoThe = tuVungTheoBoThe[boThe.id].orEmpty()
            val tongSoTu = tuVungCuaBoThe.size
            val daThuoc = tuVungCuaBoThe.count { it.trangThai == TrangThaiTuVung.DA_THUOC }
            MucThongKeBoThe(
                boTheId = boThe.id,
                tenBoThe = boThe.tenBoThe,
                tongSoTu = tongSoTu,
                soTuDaThuoc = daThuoc,
                tyLeHoanThanh = if (tongSoTu == 0) 0f else daThuoc.toFloat() / tongSoTu
            )
        }

        return ThongKeHocTapUiState(
            dangTai = false,
            tongSoBoThe = danhSachBoThe.size,
            tongSoTuVung = tongSoTuVung,
            soTuDaThuoc = soTuDaThuoc,
            soTuChuaThuoc = soTuChuaThuoc,
            soTuCanOnHomNay = soTuCanOnHomNay,
            // Chưa có bảng lịch sử ôn tập nên tạm thời chưa ghi nhận lượt ôn.
            tongLuotOnTap = 0,
            tyLeHoanThanh = tyLeHoanThanh,
            thongKeTheoBoThe = thongKeTheoBoThe
        )
    }
}