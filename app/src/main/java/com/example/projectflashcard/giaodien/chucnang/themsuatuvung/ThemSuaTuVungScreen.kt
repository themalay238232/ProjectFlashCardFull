package com.example.projectflashcard.giaodien.chucnang.themsuatuvung

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.DangTai
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe

@Composable
fun ThemSuaTuVungScreen(
    boTheId: Int,
    tuVungId: Long?,
    onQuayLai: () -> Unit,
    viewModel: ThemSuaTuVungViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(boTheId, tuVungId) {
        viewModel.taiDuLieu(boTheId, tuVungId)
    }

    LaunchedEffect(uiState.daLuu) {
        if (uiState.daLuu) {
            onQuayLai()
        }
    }

    BackHandler(onBack = onQuayLai)

    ThemSuaTuVungNoiDung(
        uiState = uiState,
        onQuayLai = onQuayLai,
        onDoiTu = viewModel::doiTu,
        onDoiNghia = viewModel::doiNghia,
        onDoiPhienAm = viewModel::doiPhienAm,
        onDoiViDu = viewModel::doiViDu,
        onLuu = viewModel::luu
    )
}

@Composable
private fun ThemSuaTuVungNoiDung(
    uiState: ThemSuaTuVungUiState,
    onQuayLai: () -> Unit,
    onDoiTu: (String) -> Unit,
    onDoiNghia: (String) -> Unit,
    onDoiPhienAm: (String) -> Unit,
    onDoiViDu: (String) -> Unit,
    onLuu: () -> Unit
) {
    val dangSua = uiState.tuVungId != null

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = if (dangSua) "Sửa từ vựng" else "Thêm từ vựng",
                coNutQuayLai = true,
                onQuayLai = onQuayLai
            )
        }
    ) { innerPadding ->
        if (uiState.dangTai) {
            DangTai(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = if (dangSua) {
                        "Đang sửa từ trong bộ: ${uiState.tenBoThe}"
                    } else {
                        "Đang thêm từ vào bộ: ${uiState.tenBoThe}"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = uiState.tu,
                        onValueChange = onDoiTu,
                        label = { Text("Từ tiếng Anh") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = uiState.nghia,
                        onValueChange = onDoiNghia,
                        label = { Text("Nghĩa tiếng Việt") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = uiState.phienAm,
                        onValueChange = onDoiPhienAm,
                        label = { Text("Phiên âm") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = uiState.viDu,
                        onValueChange = onDoiViDu,
                        label = { Text("Ví dụ") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            uiState.thongBaoLoi?.let { thongBao ->
                item {
                    Text(
                        text = thongBao,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            item {
                Button(
                    onClick = onLuu,
                    enabled = !uiState.dangLuu,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (uiState.dangLuu) "Đang lưu..." else "Lưu từ vựng")
                }
            }
            item {
                OutlinedButton(
                    onClick = onQuayLai,
                    enabled = !uiState.dangLuu,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Hủy")
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun ThemSuaTuVungScreenPreview() {
    ChuDeLearnFlash {
        ThemSuaTuVungNoiDung(
            uiState = ThemSuaTuVungUiState(boTheId = 1, tenBoThe = "English A1"),
            onQuayLai = {},
            onDoiTu = {},
            onDoiNghia = {},
            onDoiPhienAm = {},
            onDoiViDu = {},
            onLuu = {}
        )
    }
}
