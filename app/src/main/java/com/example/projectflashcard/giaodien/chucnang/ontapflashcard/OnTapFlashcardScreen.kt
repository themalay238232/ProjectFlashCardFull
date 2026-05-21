package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.thanhphan.ThanhTieuDe

@Composable
fun OnTapFlashcardScreen(
    boTheId: Int?,
    onQuayLai: () -> Unit
) {
    var daLatThe by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ThanhTieuDe(
                tieuDe = "Ôn tập Flashcard",
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = if (boTheId == null) "Ôn tập hôm nay" else "Ôn tập bộ thẻ #$boTheId",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 220.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = if (daLatThe) "cải thiện" else "Improve",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = if (daLatThe) {
                                "/ɪmˈpruːv/ - Practice helps you improve."
                            } else {
                                "Nhấn lật thẻ để xem nghĩa, phiên âm và ví dụ."
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            item {
                Button(
                    onClick = { daLatThe = !daLatThe },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (daLatThe) "Xem mặt trước" else "Lật thẻ")
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text(text = "Đã quên")
                        }
                        OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text(text = "Khó nhớ")
                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text(text = "Nhớ được")
                        }
                        OutlinedButton(onClick = {}, modifier = Modifier.weight(1f)) {
                            Text(text = "Rất dễ")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
private fun OnTapFlashcardScreenPreview() {
    ChuDeLearnFlash {
        OnTapFlashcardScreen(
            boTheId = null,
            onQuayLai = {}
        )
    }
}
