package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectflashcard.dulieu.cucbo.cosodulieu.CoSoDuLieuLearnFlash
import com.example.projectflashcard.dulieu.khodulieu.KhoDuLieuFlashcard
import com.example.projectflashcard.nghiepvu.kieudulieu.TrangThaiTuVung
import com.example.projectflashcard.nghiepvu.kieudulieu.TuVung
import com.example.projectflashcard.nghiepvu.khodulieu.KhoFlashcard
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnTapFlashcardViewModel(application: Application) : AndroidViewModel(application) {
    private val kho: KhoFlashcard
    private var congViecTai: Job? = null

    // Snapshot danh sách từ cần ôn lúc bắt đầu phiên, dùng để cập nhật lịch ôn theo index.
    private var danhSachTuVungGoc: List<TuVung> = emptyList()
    private var boTheIdHienTai: Int? = null

    private val _uiState = MutableStateFlow(OnTapFlashcardUiState())
    val uiState: StateFlow<OnTapFlashcardUiState> = _uiState.asStateFlow()

    init {
        val db = CoSoDuLieuLearnFlash.layInstance(application)
        kho = KhoDuLieuFlashcard(db.truyVanBoThe(), db.truyVanTuVung())
    }

    fun xuLySuKien(event: OnTapFlashcardEvent) {
        when (event) {
            OnTapFlashcardEvent.LatThe -> latThe()
            is OnTapFlashcardEvent.DanhGia -> danhGia(event.mucDo)
            OnTapFlashcardEvent.OnTapLai -> batDauPhien(boTheIdHienTai)
        }
    }

    /**
     * Bắt đầu một phiên ôn: lấy danh sách từ cần ôn hôm nay trong bộ thẻ
     * (hoặc trên toàn bộ bộ thẻ khi [boTheId] null) và snapshot lại.
     */
    fun batDauPhien(boTheId: Int?) {
        congViecTai?.cancel()
        boTheIdHienTai = boTheId
        _uiState.value = OnTapFlashcardUiState(dangTai = true)

        congViecTai = viewModelScope.launch {
            val tenBoThe: String
            val danhSachTuVung: List<TuVung>

            if (boTheId == null) {
                tenBoThe = "Ôn tập hôm nay"
                danhSachTuVung = kho.layTatCaBoThe().first()
                    .flatMap { boThe -> kho.layTuVungTheoBoThe(boThe.id).first() }
            } else {
                val boThe = kho.layBoTheTheoId(boTheId.toLong()).first()
                tenBoThe = boThe?.tenBoThe ?: "Bộ thẻ #$boTheId"
                danhSachTuVung = kho.layTuVungTheoBoThe(boTheId.toLong()).first()
            }

            danhSachTuVungGoc = danhSachTuVung.filter { it.canOnHomNay }

            _uiState.update {
                it.copy(
                    tenBoThe = tenBoThe,
                    dangTai = false,
                    danhSachThe = danhSachTuVungGoc.map { tuVung -> tuVung.thanhMucOnTap() }
                )
            }
        }
    }

    private fun latThe() {
        _uiState.update { it.copy(daLatThe = !it.daLatThe) }
    }

    /**
     * Người dùng tự đánh giá mức độ nhớ: chỉ cho phép khi đã lật thẻ.
     * Cập nhật lịch ôn của từ hiện tại, lưu vào lịch sử phiên rồi chuyển thẻ tiếp theo.
     */
    private fun danhGia(mucDo: MucDoNho) {
        val state = _uiState.value
        if (!state.daLatThe || state.dangXuLy) return

        val tuVung = danhSachTuVungGoc.getOrNull(state.viTriHienTai) ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(dangXuLy = true) }

            // Cập nhật lịch ôn tiếp theo dựa trên mức độ nhớ.
            runCatching { kho.suaTuVung(tuVung.apDungLichOn(mucDo)) }

            _uiState.update { hienTai ->
                val viTriTiepTheo = hienTai.viTriHienTai + 1
                hienTai.copy(
                    viTriHienTai = viTriTiepTheo,
                    daLatThe = false,
                    dangXuLy = false,
                    daHoanThanh = viTriTiepTheo >= hienTai.tongSoThe,
                    soDaQuen = hienTai.soDaQuen + if (mucDo == MucDoNho.DA_QUEN) 1 else 0,
                    soKhoNho = hienTai.soKhoNho + if (mucDo == MucDoNho.KHO_NHO) 1 else 0,
                    soNhoDuoc = hienTai.soNhoDuoc + if (mucDo == MucDoNho.NHO_DUOC) 1 else 0,
                    soRatDe = hienTai.soRatDe + if (mucDo == MucDoNho.RAT_DE) 1 else 0
                )
            }
        }
    }

    /**
     * Tính trạng thái và lịch ôn mới cho từ vựng theo mức độ nhớ:
     * - Đã quên / Khó nhớ: vẫn cần ôn lại hôm nay.
     * - Nhớ được / Rất dễ: đã ôn xong cho hôm nay.
     */
    private fun TuVung.apDungLichOn(mucDo: MucDoNho): TuVung = when (mucDo) {
        MucDoNho.DA_QUEN -> copy(trangThai = TrangThaiTuVung.MOI_HOC, canOnHomNay = true)
        MucDoNho.KHO_NHO -> copy(trangThai = TrangThaiTuVung.DANG_HOC, canOnHomNay = true)
        MucDoNho.NHO_DUOC -> copy(trangThai = TrangThaiTuVung.DANG_HOC, canOnHomNay = false)
        MucDoNho.RAT_DE -> copy(trangThai = TrangThaiTuVung.DA_THUOC, canOnHomNay = false)
    }

    private fun TuVung.thanhMucOnTap(): MucOnTap = MucOnTap(
        id = id,
        tu = tu,
        nghia = nghia,
        phienAm = phienAm,
        viDu = viDu
    )
}