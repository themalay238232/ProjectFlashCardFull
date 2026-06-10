package com.example.projectflashcard.giaodien.chucnang.thongkehoctap

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectflashcard.giaodien.chude.Cam
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.chude.DoNhe
import com.example.projectflashcard.giaodien.chude.XanhChuDao
import com.example.projectflashcard.giaodien.chude.XanhDam
import com.example.projectflashcard.giaodien.chude.XanhLa
import com.example.projectflashcard.giaodien.thanhphan.DangTai
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe
import com.example.projectflashcard.giaodien.thanhphan.TrangThaiRong

@Composable
fun ThongKeHocTapScreen(
    onQuayLai: () -> Unit,
    viewModel: ThongKeHocTapViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Thống kê học tập",
                coNutQuayLai = true,
                onQuayLai = onQuayLai
            )
        }
    ) { innerPadding ->
        when {
            uiState.dangTai -> DangTai(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            uiState.tongSoTuVung == 0 && uiState.tongSoBoThe == 0 -> TrangThaiRong(
                tieuDe = "Chưa có dữ liệu thống kê",
                moTa = "Hãy tạo bộ thẻ và thêm từ vựng để bắt đầu theo dõi tiến độ học tập.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            else -> ThongKeHocTapNoiDung(
                uiState = uiState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Composable
private fun ThongKeHocTapNoiDung(
    uiState: ThongKeHocTapUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
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
            TheTyLeHoanThanh(
                phanTram = uiState.phanTramHoanThanh,
                soTuDaThuoc = uiState.soTuDaThuoc,
                tongSoTuVung = uiState.tongSoTuVung
            )
        }

        item {
            TheThongKeHocTap(
                nhan = "Tổng số bộ thẻ",
                giaTri = uiState.tongSoBoThe.toString(),
                moTa = "Số bộ thẻ đang có trong ứng dụng",
                bieuTuong = Icons.AutoMirrored.Filled.List,
                mauNhan = XanhChuDao
            )
        }
        item {
            TheThongKeHocTap(
                nhan = "Tổng số từ vựng",
                giaTri = uiState.tongSoTuVung.toString(),
                moTa = "Toàn bộ từ vựng đã lưu",
                bieuTuong = Icons.Default.Star,
                mauNhan = XanhDam
            )
        }
        item {
            TheThongKeHocTap(
                nhan = "Số từ đã thuộc",
                giaTri = uiState.soTuDaThuoc.toString(),
                moTa = "Các từ ở trạng thái đã thuộc",
                bieuTuong = Icons.Default.CheckCircle,
                mauNhan = XanhLa
            )
        }
        item {
            TheThongKeHocTap(
                nhan = "Số từ chưa thuộc",
                giaTri = uiState.soTuChuaThuoc.toString(),
                moTa = "Các từ cần tiếp tục ôn luyện",
                bieuTuong = Icons.Default.Info,
                mauNhan = Cam
            )
        }
        item {
            TheThongKeHocTap(
                nhan = "Số từ cần ôn hôm nay",
                giaTri = uiState.soTuCanOnHomNay.toString(),
                moTa = "Từ chưa thuộc đến hạn ôn tập",
                bieuTuong = Icons.Default.Notifications,
                mauNhan = DoNhe
            )
        }
        item {
            TheThongKeHocTap(
                nhan = "Tổng lượt ôn tập",
                giaTri = uiState.tongLuotOnTap.toString(),
                moTa = "Số lượt ôn tập đã ghi nhận",
                bieuTuong = Icons.Default.Refresh,
                mauNhan = XanhChuDao
            )
        }

        if (uiState.thongKeTheoBoThe.isNotEmpty()) {
            item {
                Text(
                    text = "Thống kê theo bộ thẻ",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            items(uiState.thongKeTheoBoThe, key = { it.boTheId }) { muc ->
                HangThongKeBoThe(muc = muc)
            }
        }
    }
}

@Composable
private fun TheTyLeHoanThanh(
    phanTram: Int,
    soTuDaThuoc: Int,
    tongSoTuVung: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Tỷ lệ hoàn thành",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = "$phanTram%",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            LinearProgressIndicator(
                progress = { if (tongSoTuVung == 0) 0f else soTuDaThuoc.toFloat() / tongSoTuVung },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "$soTuDaThuoc / $tongSoTuVung từ đã thuộc",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun HangThongKeBoThe(
    muc: MucThongKeBoThe,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = muc.tenBoThe,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${(muc.tyLeHoanThanh * 100).toInt()}%",
                    style = MaterialTheme.typography.titleMedium,
                    color = XanhLa,
                    textAlign = TextAlign.End
                )
            }
            LinearProgressIndicator(
                progress = { muc.tyLeHoanThanh },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "${muc.soTuDaThuoc} / ${muc.tongSoTu} từ đã thuộc",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun ThongKeHocTapNoiDungPreview() {
    ChuDeLearnFlash {
        ThongKeHocTapNoiDung(
            uiState = ThongKeHocTapUiState(
                dangTai = false,
                tongSoBoThe = 3,
                tongSoTuVung = 28,
                soTuDaThuoc = 12,
                soTuChuaThuoc = 16,
                soTuCanOnHomNay = 7,
                tongLuotOnTap = 9,
                tyLeHoanThanh = 12f / 28f,
                thongKeTheoBoThe = listOf(
                    MucThongKeBoThe(1, "Tiếng Anh giao tiếp", 12, 8, 8f / 12f),
                    MucThongKeBoThe(2, "TOEIC 600", 16, 4, 4f / 16f)
                )
            )
        )
    }
}