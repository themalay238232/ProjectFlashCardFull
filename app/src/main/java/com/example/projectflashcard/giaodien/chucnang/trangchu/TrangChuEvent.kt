package com.example.projectflashcard.giaodien.chucnang.trangchu

sealed interface TrangChuEvent {
    data object MoQuanLyBoThe : TrangChuEvent
    data object MoOnTapHomNay : TrangChuEvent
    data object MoThongKeHocTap : TrangChuEvent
    data object MoTimKiemTuVung : TrangChuEvent
    data object MoCaiDat : TrangChuEvent
    data object MoGioiThieu : TrangChuEvent
}

