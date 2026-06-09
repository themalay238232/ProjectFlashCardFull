package com.example.projectflashcard.giaodien.chucnang.ontapflashcard

/**
 * Bốn mức tự đánh giá mức độ nhớ sau khi lật thẻ.
 * Mỗi mức quyết định lịch ôn tiếp theo của từ vựng.
 */
enum class MucDoNho(val nhan: String) {
    DA_QUEN("Đã quên"),
    KHO_NHO("Khó nhớ"),
    NHO_DUOC("Nhớ được"),
    RAT_DE("Rất dễ")
}

sealed interface OnTapFlashcardEvent {
    data object LatThe : OnTapFlashcardEvent
    data class DanhGia(val mucDo: MucDoNho) : OnTapFlashcardEvent
    data object OnTapLai : OnTapFlashcardEvent
}