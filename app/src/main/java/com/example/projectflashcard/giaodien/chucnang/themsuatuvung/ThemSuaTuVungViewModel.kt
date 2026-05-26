package com.example.projectflashcard.giaodien.chucnang.themsuatuvung

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectflashcard.dulieu.cucbo.cosodulieu.CoSoDuLieuLearnFlash
import com.example.projectflashcard.dulieu.khodulieu.KhoDuLieuFlashcard
import com.example.projectflashcard.nghiepvu.kieudulieu.TuVung
import com.example.projectflashcard.nghiepvu.khodulieu.KhoFlashcard
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThemSuaTuVungViewModel(application: Application) : AndroidViewModel(application) {
    private val kho: KhoFlashcard
    private var congViecTai: Job? = null

    private val _uiState = MutableStateFlow(ThemSuaTuVungUiState())
    val uiState: StateFlow<ThemSuaTuVungUiState> = _uiState.asStateFlow()

    init {
        val db = CoSoDuLieuLearnFlash.layInstance(application)
        kho = KhoDuLieuFlashcard(db.truyVanBoThe(), db.truyVanTuVung())
    }

    fun taiDuLieu(boTheId: Int, tuVungId: Long?) {
        congViecTai?.cancel()
        congViecTai = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    boTheId = boTheId,
                    tuVungId = tuVungId,
                    dangTai = true,
                    daLuu = false,
                    thongBaoLoi = null
                )
            }

            val boThe = kho.layBoTheTheoId(boTheId.toLong()).first()
            val tuVung = tuVungId?.let { kho.layTuVungTheoId(it).first() }

            _uiState.update { state ->
                if (tuVung == null) {
                    state.copy(
                        tenBoThe = boThe?.tenBoThe ?: "Bộ thẻ #$boTheId",
                        tuVungGoc = null,
                        tu = "",
                        nghia = "",
                        phienAm = "",
                        viDu = "",
                        dangTai = false
                    )
                } else {
                    state.copy(
                        tenBoThe = boThe?.tenBoThe ?: "Bộ thẻ #$boTheId",
                        tuVungGoc = tuVung,
                        tu = tuVung.tu,
                        nghia = tuVung.nghia,
                        phienAm = tuVung.phienAm,
                        viDu = tuVung.viDu,
                        dangTai = false
                    )
                }
            }
        }
    }

    fun doiTu(tu: String) {
        _uiState.update { it.copy(tu = tu, thongBaoLoi = null) }
    }

    fun doiNghia(nghia: String) {
        _uiState.update { it.copy(nghia = nghia, thongBaoLoi = null) }
    }

    fun doiPhienAm(phienAm: String) {
        _uiState.update { it.copy(phienAm = phienAm) }
    }

    fun doiViDu(viDu: String) {
        _uiState.update { it.copy(viDu = viDu) }
    }

    fun luu() {
        val state = _uiState.value
        val tu = state.tu.trim()
        val nghia = state.nghia.trim()

        if (tu.isEmpty()) {
            _uiState.update { it.copy(thongBaoLoi = "Từ vựng không được để trống") }
            return
        }
        if (nghia.isEmpty()) {
            _uiState.update { it.copy(thongBaoLoi = "Nghĩa tiếng Việt không được để trống") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(dangLuu = true, thongBaoLoi = null) }
            runCatching {
                val tuVungGoc = state.tuVungGoc
                val biTrung = kho.layTuVungTheoBoThe(state.boTheId.toLong())
                    .first()
                    .any { tuVung ->
                        tuVung.tu.trim().equals(tu, ignoreCase = true) && tuVung.id != tuVungGoc?.id
                    }

                if (biTrung) {
                    error("Từ này đã tồn tại trong bộ thẻ hiện tại")
                }

                if (tuVungGoc == null) {
                    kho.themTuVung(
                        TuVung(
                            boTheId = state.boTheId.toLong(),
                            tu = tu,
                            nghia = nghia,
                            phienAm = state.phienAm.trim(),
                            viDu = state.viDu.trim()
                        )
                    )
                } else {
                    kho.suaTuVung(
                        tuVungGoc.copy(
                            tu = tu,
                            nghia = nghia,
                            phienAm = state.phienAm.trim(),
                            viDu = state.viDu.trim()
                        )
                    )
                }
            }.onSuccess {
                _uiState.update { it.copy(dangLuu = false, daLuu = true) }
            }.onFailure { loi ->
                _uiState.update {
                    it.copy(
                        dangLuu = false,
                        thongBaoLoi = loi.message ?: "Không lưu được từ vựng"
                    )
                }
            }
        }
    }
}
