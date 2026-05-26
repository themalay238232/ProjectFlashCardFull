package com.example.projectflashcard.giaodien.dieuhuong

sealed class ManHinh(
    val route: String,
    val tieuDe: String
) {
    data object TrangChu : ManHinh("trang_chu", "Trang chủ")
    data object QuanLyBoThe : ManHinh("quan_ly_bo_the", "Quản lý bộ thẻ")

    data object ChiTietBoThe : ManHinh("chi_tiet_bo_the/{boTheId}", "Chi tiết bộ thẻ") {
        const val BO_THE_ID = "boTheId"

        fun taoDuongDan(boTheId: Int): String = "chi_tiet_bo_the/$boTheId"
    }

    data object ThemSuaTuVung : ManHinh(
        "them_sua_tu_vung/{boTheId}?tuVungId={tuVungId}",
        "Thêm / sửa từ vựng"
    ) {
        const val BO_THE_ID = "boTheId"
        const val TU_VUNG_ID = "tuVungId"

        fun taoDuongDan(boTheId: Int, tuVungId: Long? = null): String {
            return if (tuVungId == null) {
                "them_sua_tu_vung/$boTheId"
            } else {
                "them_sua_tu_vung/$boTheId?tuVungId=$tuVungId"
            }
        }
    }

    data object OnTapFlashcard : ManHinh("on_tap_flashcard?boTheId={boTheId}", "Ôn tập Flashcard") {
        const val BO_THE_ID = "boTheId"

        fun taoDuongDan(boTheId: Int? = null): String {
            return if (boTheId == null) {
                "on_tap_flashcard"
            } else {
                "on_tap_flashcard?boTheId=$boTheId"
            }
        }
    }

    data object ThongKeHocTap : ManHinh("thong_ke_hoc_tap", "Thống kê học tập")
    data object TimKiemTuVung : ManHinh("tim_kiem_tu_vung", "Tìm kiếm từ vựng")
    data object CaiDat : ManHinh("cai_dat", "Cài đặt")
    data object GioiThieu : ManHinh("gioi_thieu", "Giới thiệu")
}
