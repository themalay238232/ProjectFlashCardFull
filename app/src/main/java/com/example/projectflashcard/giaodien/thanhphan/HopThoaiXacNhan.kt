package com.example.projectflashcard.giaodien.thanhphan

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun HopThoaiXacNhan(
    tieuDe: String,
    noiDung: String,
    chuXacNhan: String = "Xác nhận",
    chuHuy: String = "Hủy",
    onXacNhan: () -> Unit,
    onHuy: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onHuy,
        title = { Text(text = tieuDe) },
        text = { Text(text = noiDung) },
        confirmButton = {
            TextButton(onClick = onXacNhan) {
                Text(text = chuXacNhan)
            }
        },
        dismissButton = {
            TextButton(onClick = onHuy) {
                Text(text = chuHuy)
            }
        }
    )
}

