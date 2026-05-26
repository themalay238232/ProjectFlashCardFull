package com.example.projectflashcard

import androidx.compose.runtime.Composable
import com.example.projectflashcard.giaodien.chude.ChuDeLearnFlash
import com.example.projectflashcard.giaodien.dieuhuong.SoDoDieuHuong

@Composable
fun UngDungLearnFlash() {
    ChuDeLearnFlash {
        SoDoDieuHuong()
    }
}

