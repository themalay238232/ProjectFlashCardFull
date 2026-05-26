package com.example.projectflashcard.giaodien.chucnang.timkiemtuvung

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TheTuVung
import com.example.projectflashcard.giaodien.thanhphan.TrangThaiRong

private data class TuVungMau(
    val tu: String,
    val nghia: String,
    val phienAm: String,
    val viDu: String
)

@Composable
fun TimKiemTuVungScreen(onQuayLai: () -> Unit) {
    var tuKhoa by remember { mutableStateOf("") }
    val danhSach = listOf(
        TuVungMau("Apple", "quả táo", "/ˈæp.əl/", "I eat an apple every morning."),
        TuVungMau("Improve", "cải thiện", "/ɪmˈpruːv/", "Practice helps you improve."),
        TuVungMau("Memory", "trí nhớ", "/ˈmem.ər.i/", "Flashcards support long-term memory."),
        TuVungMau("Review", "ôn tập", "/rɪˈvjuː/", "Review your cards every day.")
    )
    val ketQua = danhSach.filter {
        tuKhoa.isBlank() ||
            it.tu.contains(tuKhoa, ignoreCase = true) ||
            it.nghia.contains(tuKhoa, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Tìm kiếm từ vựng",
                coNutQuayLai = true,
                onQuayLai = onQuayLai
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                OutlinedTextField(
                    value = tuKhoa,
                    onValueChange = { tuKhoa = it },
                    label = { Text("Nhập từ tiếng Anh hoặc nghĩa tiếng Việt") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (ketQua.isEmpty()) {
                item {
                    TrangThaiRong(
                        tieuDe = "Không có kết quả",
                        moTa = "Không tìm thấy từ vựng phù hợp."
                    )
                }
            } else {
                items(ketQua) { tu ->
                    TheTuVung(
                        tuTiengAnh = tu.tu,
                        nghiaTiengViet = tu.nghia,
                        phienAm = tu.phienAm,
                        viDu = tu.viDu
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun TimKiemTuVungScreenPreview() {
    ChuDeLearnFlash {
        TimKiemTuVungScreen(onQuayLai = {})
    }
}
