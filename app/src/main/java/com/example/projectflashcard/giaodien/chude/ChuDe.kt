package com.example.projectflashcard.giaodien.chude

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val BangMauSang = lightColorScheme(
    primary = XanhChuDao,
    onPrimary = Color.White,
    primaryContainer = XanhNhat,
    onPrimaryContainer = XanhDam,
    secondary = XanhLa,
    onSecondary = Color.White,
    tertiary = Cam,
    background = NenSang,
    onBackground = ChuChinh,
    surface = BeMatSang,
    onSurface = ChuChinh,
    surfaceVariant = Color(0xFFE2E8F0),
    onSurfaceVariant = ChuPhu,
    error = DoNhe
)

private val BangMauToi = darkColorScheme(
    primary = Color(0xFF93C5FD),
    onPrimary = Color(0xFF0F172A),
    primaryContainer = XanhDam,
    onPrimaryContainer = Color(0xFFEFF6FF),
    secondary = Color(0xFF86EFAC),
    tertiary = Color(0xFFFDBA74),
    background = Color(0xFF020617),
    onBackground = Color(0xFFE2E8F0),
    surface = Color(0xFF0F172A),
    onSurface = Color(0xFFE2E8F0),
    surfaceVariant = Color(0xFF1E293B),
    onSurfaceVariant = Color(0xFFCBD5E1)
)

@Composable
fun ChuDeLearnFlash(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) BangMauToi else BangMauSang,
        typography = KieuChuLearnFlash,
        content = content
    )
}

