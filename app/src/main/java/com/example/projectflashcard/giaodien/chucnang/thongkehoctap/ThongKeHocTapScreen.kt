package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.Cam
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.chude.XanhChuDao
import com.example.projectflashcard.giaodien.chude.XanhLa
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TheThongKe

@Composable
fun ThongKeHocTapScreen(onQuayLai: () -> Unit) {
    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Thống kê học tập",
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
                    text = "Tổng quan tiến độ",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            item {
                TheThongKe(
                    nhan = "Tỷ lệ ghi nhớ",
                    giaTri = "43%",
                    moTa = "12 / 28 từ đã thuộc",
                    mauNhan = XanhLa
                )
            }
            item {
                TheThongKe(
                    nhan = "Chuỗi ngày học",
                    giaTri = "5 ngày",
                    moTa = "Số liệu thống kê quá trình học",
                    mauNhan = XanhChuDao
                )
            }
            item {
                TheThongKe(
                    nhan = "Phiên ôn tập",
                    giaTri = "9",
                    moTa = "Tổng số phiên trong tuần này",
                    mauNhan = Cam
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun ThongKeHocTapScreenPreview() {
    ChuDeLearnFlash {
        ThongKeHocTapScreen(onQuayLai = {})
    }
}
