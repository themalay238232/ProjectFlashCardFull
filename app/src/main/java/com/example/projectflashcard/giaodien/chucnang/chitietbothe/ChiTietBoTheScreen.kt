package com.example.projectflashcard.giaodien.chucnang.chitietbothe

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TheTuVung

@Composable
fun ChiTietBoTheScreen(
    boTheId: Int,
    onQuayLai: () -> Unit,
    onThemTuVung: (Int) -> Unit,
    onOnTapBoThe: (Int) -> Unit
) {
    val tenBoThe = when (boTheId) {
        2 -> "Giao tiếp hằng ngày"
        3 -> "Công nghệ thông tin"
        else -> "English A1"
    }
    val tuVungMau = listOf(
        listOf("Apple", "quả táo", "/ˈæp.əl/", "I eat an apple every morning."),
        listOf("Improve", "cải thiện", "/ɪmˈpruːv/", "Practice helps you improve."),
        listOf("Memory", "trí nhớ", "/ˈmem.ər.i/", "Flashcards support long-term memory.")
    )

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = tenBoThe,
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
                Text(
                    text = "Danh sách từ vựng",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { onThemTuVung(boTheId) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Thêm từ vựng")
                    }
                    OutlinedButton(
                        onClick = { onOnTapBoThe(boTheId) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Ôn tập")
                    }
                }
            }
            items(tuVungMau.size) { index ->
                val tu = tuVungMau[index]
                TheTuVung(
                    tuTiengAnh = tu[0],
                    nghiaTiengViet = tu[1],
                    phienAm = tu[2],
                    viDu = tu[3],
                    onClick = { onThemTuVung(boTheId) }
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun ChiTietBoTheScreenPreview() {
    ChuDeLearnFlash {
        ChiTietBoTheScreen(
            boTheId = 1,
            onQuayLai = {},
            onThemTuVung = {},
            onOnTapBoThe = {}
        )
    }
}
