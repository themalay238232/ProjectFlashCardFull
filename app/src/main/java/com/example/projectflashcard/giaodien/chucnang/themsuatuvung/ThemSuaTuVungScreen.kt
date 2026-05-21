package com.example.projectflashcard.giaodien.chucnang.themsuatuvung

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe

@Composable
fun ThemSuaTuVungScreen(
    boTheId: Int,
    onQuayLai: () -> Unit
) {
    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Thêm / sửa từ vựng",
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Bộ thẻ #$boTheId",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    OutlinedTextField(
                        value = "Practice",
                        onValueChange = {},
                        label = { Text("Từ tiếng Anh") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = "luyện tập",
                        onValueChange = {},
                        label = { Text("Nghĩa tiếng Việt") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = "/ˈpræk.tɪs/",
                        onValueChange = {},
                        label = { Text("Phiên âm") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = "You should practice every day.",
                        onValueChange = {},
                        label = { Text("Ví dụ") },
                        minLines = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                Button(
                    onClick = onQuayLai,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Lưu từ vựng")
                }
            }
            item {
                OutlinedButton(
                    onClick = onQuayLai,
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
        ThemSuaTuVungScreen(
            boTheId = 1,
            onQuayLai = {}
        )
    }
}
