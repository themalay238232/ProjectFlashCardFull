package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

import com.example.projectflashcard.nghiepvu.kieudulieu.MucDoOnTap

sealed interface OnTapFlashcardEvent {
    data object LatThe : OnTapFlashcardEvent
    data class DanhGia(val mucDo: MucDoOnTap) : OnTapFlashcardEvent
    data object OnTapLai : OnTapFlashcardEvent
}
