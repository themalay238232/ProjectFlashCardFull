package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.DangTai
import com.example.projectflashcard.giaodien.thanhphan.HienThiLoi
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TrangThaiRong
import com.example.projectflashcard.nghiepvu.kieudulieu.MucDoOnTap

@Composable
fun OnTapFlashcardScreen(
    boTheId: Int?,
    onQuayLai: () -> Unit,
    viewModel: OnTapFlashcardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(boTheId) {
        viewModel.batDauPhien(boTheId)
    }

    OnTapFlashcardNoiDung(
        uiState = uiState,
        onQuayLai = onQuayLai,
        onEvent = viewModel::xuLySuKien
    )
}

@Composable
private fun OnTapFlashcardNoiDung(
    uiState: OnTapFlashcardUiState,
    onQuayLai: () -> Unit,
    onEvent: (OnTapFlashcardEvent) -> Unit
) {
    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = uiState.tenPhienOnTap,
                coNutQuayLai = true,
                onQuayLai = onQuayLai
            )
        }
    ) { innerPadding ->
        val modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)

        when {
            uiState.dangTai -> DangTai(modifier = modifier)
            uiState.rong -> TrangThaiRong(
                modifier = modifier,
                tieuDe = "Chua co tu nao can on",
                moTa = "Hay them tu vung hoac danh dau tu can on de bat dau."
            )
            uiState.daHoanThanh -> ManHinhHoanThanhOnTap(
                modifier = modifier,
                uiState = uiState,
                onOnTapLai = { onEvent(OnTapFlashcardEvent.OnTapLai) },
                onQuayLai = onQuayLai
            )
            else -> PhienOnTap(
                modifier = modifier,
                uiState = uiState,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun PhienOnTap(
    uiState: OnTapFlashcardUiState,
    onEvent: (OnTapFlashcardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val the = uiState.theHienTai ?: return

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Tien do ${uiState.tienDo}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        item {
            TheFlashcard(
                the = the,
                daLatThe = uiState.daLatThe,
                onLatThe = { onEvent(OnTapFlashcardEvent.LatThe) }
            )
        }
        item {
            Button(
                onClick = { onEvent(OnTapFlashcardEvent.LatThe) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (uiState.daLatThe) "Xem mat truoc" else "Lat the")
            }
        }
        uiState.thongBaoLoi?.let { loi ->
            item {
                HienThiLoi(
                    thongBao = loi,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        item {
            Text(
                text = if (uiState.daLatThe) {
                    "Ban nho tu nay o muc nao?"
                } else {
                    "Lat the truoc khi danh gia."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        item {
            val choPhep = uiState.daLatThe && !uiState.dangXuLy
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    NutDanhGia(MucDoOnTap.DA_QUEN, choPhep, onEvent, Modifier.weight(1f))
                    NutDanhGia(MucDoOnTap.KHO_NHO, choPhep, onEvent, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    NutDanhGia(MucDoOnTap.NHO_DUOC, choPhep, onEvent, Modifier.weight(1f))
                    NutDanhGia(MucDoOnTap.RAT_DE, choPhep, onEvent, Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun NutDanhGia(
    mucDo: MucDoOnTap,
    choPhep: Boolean,
    onEvent: (OnTapFlashcardEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { onEvent(OnTapFlashcardEvent.DanhGia(mucDo)) },
        enabled = choPhep,
        modifier = modifier
    ) {
        Text(text = mucDo.nhanHienThi())
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun OnTapFlashcardScreenPreview() {
    ChuDeLearnFlash {
        OnTapFlashcardNoiDung(
            uiState = OnTapFlashcardUiState(
                tenPhienOnTap = "English A1",
                dangTai = false,
                danhSachThe = listOf(
                    MucOnTap(
                        id = 1,
                        tu = "improve",
                        nghia = "cai thien",
                        phienAm = "/im-pru:v/",
                        viDu = "Practice helps you improve."
                    )
                )
            ),
            onQuayLai = {},
            onEvent = {}
        )
    }
}
