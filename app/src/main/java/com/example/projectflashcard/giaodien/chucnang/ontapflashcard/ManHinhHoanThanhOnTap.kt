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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.nghiepvu.kieudulieu.MucDoOnTap

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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hoan thanh phien on tap",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Ban da on ${uiState.tongSoThe} the.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        DongThongKe(MucDoOnTap.DA_QUEN.nhanHienThi(), uiState.soDaQuen)
        DongThongKe(MucDoOnTap.KHO_NHO.nhanHienThi(), uiState.soKhoNho)
        DongThongKe(MucDoOnTap.NHO_DUOC.nhanHienThi(), uiState.soNhoDuoc)
        DongThongKe(MucDoOnTap.RAT_DE.nhanHienThi(), uiState.soRatDe)

        Button(
            onClick = onOnTapLai,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "On tap lai")
        }
        OutlinedButton(
            onClick = onQuayLai,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Quay lai")
        }
    }
}

@Composable
private fun DongThongKe(nhan: String, soLuong: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = nhan, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = soLuong.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
