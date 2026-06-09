package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Thẻ flashcard hai mặt. Khi chưa lật hiển thị mặt trước (từ vựng),
 * khi đã lật hiển thị mặt sau (nghĩa, phiên âm, ví dụ).
 * Bấm vào thẻ cũng là một cách lật thẻ.
 */
@Composable
fun TheFlashcard(
    the: MucOnTap,
    daLatThe: Boolean,
    onLatThe: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 240.dp)
            .clickable(onClick = onLatThe),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (daLatThe) {
                MaterialTheme.colorScheme.secondaryContainer
            } else {
                MaterialTheme.colorScheme.primaryContainer
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (daLatThe) {
                MatSau(the)
            } else {
                MatTruoc(the)
            }
        }
    }
}

@Composable
private fun MatTruoc(the: MucOnTap) {
    Text(
        text = the.tu,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center
    )
    Text(
        text = "Nhấn để lật thẻ và xem nghĩa",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun MatSau(the: MucOnTap) {
    Text(
        text = the.nghia,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        textAlign = TextAlign.Center
    )
    if (the.phienAm.isNotBlank()) {
        Text(
            text = the.phienAm,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
    }
    if (the.viDu.isNotBlank()) {
        Text(
            text = the.viDu,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Center
        )
    }
}