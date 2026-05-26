package com.example.projectflashcard.giaodien.chucnang.trangchu

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TrangChuViewModel : ViewModel() {
    var uiState by mutableStateOf(TrangChuUiState())
        private set
}

