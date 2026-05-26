package com.example.projectflashcard.giaodien.thanhphan

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThanhTieuDe(
    tieuDe: String,
    coNutQuayLai: Boolean = false,
    onQuayLai: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = tieuDe,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (coNutQuayLai) {
                IconButton(onClick = onQuayLai) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Quay lại"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
