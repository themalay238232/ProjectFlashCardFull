package com.example.projectflashcard.giaodien.chucnang.quanlybothe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe

private data class BoTheMau(
    val id: Int,
    val ten: String,
    val moTa: String,
    val soTu: Int,
    val canOn: Int
)

@Composable
fun QuanLyBoTheScreen(
    onQuayLai: () -> Unit,
    onMoChiTiet: (Int) -> Unit
) {
    val danhSach = listOf(
        BoTheMau(1, "English A1", "Từ vựng nền tảng cho người mới bắt đầu", 10, 3),
        BoTheMau(2, "Giao tiếp hằng ngày", "Các cụm từ dùng trong tình huống quen thuộc", 8, 2),
        BoTheMau(3, "Công nghệ thông tin", "Từ vựng chuyên ngành IT cơ bản", 10, 1)
    )

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Quản lý bộ thẻ",
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
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Thêm bộ thẻ")
                }
            }
            items(danhSach) { boThe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMoChiTiet(boThe.id) },
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = boThe.ten,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${boThe.soTu} từ",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = boThe.moTa,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "${boThe.canOn} từ cần ôn hôm nay",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun QuanLyBoTheScreenPreview() {
    ChuDeLearnFlash {
        QuanLyBoTheScreen(
            onQuayLai = {},
            onMoChiTiet = {}
        )
    }
}
