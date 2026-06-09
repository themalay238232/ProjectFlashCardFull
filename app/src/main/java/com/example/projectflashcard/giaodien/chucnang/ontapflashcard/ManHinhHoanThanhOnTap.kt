package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Màn hiển thị khi đã ôn hết các thẻ trong phiên: tổng kết số thẻ
 * theo từng mức đánh giá, cho phép ôn lại hoặc quay về.
 */
@Composable
fun ManHinhHoanThanhOnTap(
    uiState: OnTapFlashcardUiState,
    onOnTapLai: () -> Unit,
    onQuayLai: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Hoàn thành phiên ôn tập!",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Bạn đã ôn ${uiState.tongSoThe} thẻ trong bộ \"${uiState.tenBoThe}\".",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DongThongKe(MucDoNho.DA_QUEN.nhan, uiState.soDaQuen)
                DongThongKe(MucDoNho.KHO_NHO.nhan, uiState.soKhoNho)
                DongThongKe(MucDoNho.NHO_DUOC.nhan, uiState.soNhoDuoc)
                DongThongKe(MucDoNho.RAT_DE.nhan, uiState.soRatDe)
            }
        }

        Button(
            onClick = onOnTapLai,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ôn lại bộ thẻ")
        }
        OutlinedButton(
            onClick = onQuayLai,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Quay lại")
        }
    }
}

@Composable
private fun DongThongKe(nhan: String, soLuong: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = nhan,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = soLuong.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}