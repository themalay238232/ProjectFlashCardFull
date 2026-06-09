package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TrangThaiRong

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
                tieuDe = uiState.tenBoThe.ifBlank { "Ôn tập Flashcard" },
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
                tieuDe = "Chưa có từ nào cần ôn",
                moTa = "Hôm nay chưa có từ nào cần ôn trong bộ thẻ này."
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

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tiến độ ${uiState.tienDo}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        TheFlashcard(
            the = the,
            daLatThe = uiState.daLatThe,
            onLatThe = { onEvent(OnTapFlashcardEvent.LatThe) }
        )

        Button(
            onClick = { onEvent(OnTapFlashcardEvent.LatThe) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (uiState.daLatThe) "Xem mặt trước" else "Lật thẻ")
        }

        // Chưa lật thẻ thì chưa cho đánh giá.
        Text(
            text = if (uiState.daLatThe) {
                "Bạn nhớ từ này ở mức nào?"
            } else {
                "Lật thẻ để tự đánh giá mức độ nhớ."
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        BangDanhGia(
            choPhep = uiState.daLatThe && !uiState.dangXuLy,
            onDanhGia = { mucDo -> onEvent(OnTapFlashcardEvent.DanhGia(mucDo)) }
        )
    }
}

@Composable
private fun BangDanhGia(
    choPhep: Boolean,
    onDanhGia: (MucDoNho) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NutDanhGia(MucDoNho.DA_QUEN, choPhep, onDanhGia, Modifier.weight(1f))
            NutDanhGia(MucDoNho.KHO_NHO, choPhep, onDanhGia, Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            NutDanhGia(MucDoNho.NHO_DUOC, choPhep, onDanhGia, Modifier.weight(1f))
            NutDanhGia(MucDoNho.RAT_DE, choPhep, onDanhGia, Modifier.weight(1f))
        }
    }
}

@Composable
private fun NutDanhGia(
    mucDo: MucDoNho,
    choPhep: Boolean,
    onDanhGia: (MucDoNho) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = { onDanhGia(mucDo) },
        enabled = choPhep,
        modifier = modifier
    ) {
        Text(text = mucDo.nhan)
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun OnTapFlashcardScreenPreview() {
    ChuDeLearnFlash {
        OnTapFlashcardNoiDung(
            uiState = OnTapFlashcardUiState(
                tenBoThe = "English A1",
                dangTai = false,
                danhSachThe = listOf(
                    MucOnTap(
                        id = 1,
                        tu = "improve",
                        nghia = "cải thiện",
                        phienAm = "/ɪmˈpruːv/",
                        viDu = "Practice helps you improve."
                    )
                )
            ),
            onQuayLai = {},
            onEvent = {}
        )
    }
}