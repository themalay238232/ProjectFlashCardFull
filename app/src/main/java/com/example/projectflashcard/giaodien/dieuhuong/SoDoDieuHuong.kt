package com.example.projectflashcard.giaodien.dieuhuong

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectflashcard.giaodien.chucnang.caidat.CaiDatScreen
import com.example.projectflashcard.giaodien.chucnang.chitietbothe.ChiTietBoTheScreen
import com.example.projectflashcard.giaodien.chucnang.gioithieu.GioiThieuScreen
import com.example.projectflashcard.giaodien.chucnang.ontapflashcard.OnTapFlashcardScreen
import com.example.projectflashcard.giaodien.chucnang.quanlybothe.QuanLyBoTheScreen
import com.example.projectflashcard.giaodien.chucnang.themsuatuvung.ThemSuaTuVungScreen
import com.example.projectflashcard.giaodien.chucnang.thongkehoctap.ThongKeHocTapScreen
import com.example.projectflashcard.giaodien.chucnang.timkiemtuvung.TimKiemTuVungScreen
import com.example.projectflashcard.giaodien.chucnang.trangchu.TrangChuEvent
import com.example.projectflashcard.giaodien.chucnang.trangchu.TrangChuScreen

@Composable
fun SoDoDieuHuong(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ManHinh.TrangChu.route
    ) {
        composable(ManHinh.TrangChu.route) {
            TrangChuScreen(
                onEvent = { event ->
                    when (event) {
                        TrangChuEvent.MoQuanLyBoThe -> navController.navigate(ManHinh.QuanLyBoThe.route)
                        TrangChuEvent.MoOnTapHomNay -> navController.navigate(ManHinh.OnTapFlashcard.taoDuongDan())
                        TrangChuEvent.MoThongKeHocTap -> navController.navigate(ManHinh.ThongKeHocTap.route)
                        TrangChuEvent.MoTimKiemTuVung -> navController.navigate(ManHinh.TimKiemTuVung.route)
                        TrangChuEvent.MoCaiDat -> navController.navigate(ManHinh.CaiDat.route)
                        TrangChuEvent.MoGioiThieu -> navController.navigate(ManHinh.GioiThieu.route)
                    }
                }
            )
        }

        composable(ManHinh.QuanLyBoThe.route) {
            QuanLyBoTheScreen(
                onQuayLai = { navController.popBackStack() },
                onMoChiTiet = { boTheId ->
                    navController.navigate(ManHinh.ChiTietBoThe.taoDuongDan(boTheId))
                }
            )
        }

        composable(
            route = ManHinh.ChiTietBoThe.route,
            arguments = listOf(
                navArgument(ManHinh.ChiTietBoThe.BO_THE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val boTheId = backStackEntry.arguments?.getInt(ManHinh.ChiTietBoThe.BO_THE_ID) ?: 1
            ChiTietBoTheScreen(
                boTheId = boTheId,
                onQuayLai = { navController.popBackStack() },
                onThemTuVung = { id -> navController.navigate(ManHinh.ThemSuaTuVung.taoDuongDan(id)) },
                onOnTapBoThe = { id -> navController.navigate(ManHinh.OnTapFlashcard.taoDuongDan(id)) }
            )
        }

        composable(
            route = ManHinh.ThemSuaTuVung.route,
            arguments = listOf(
                navArgument(ManHinh.ThemSuaTuVung.BO_THE_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val boTheId = backStackEntry.arguments?.getInt(ManHinh.ThemSuaTuVung.BO_THE_ID) ?: 1
            ThemSuaTuVungScreen(
                boTheId = boTheId,
                onQuayLai = { navController.popBackStack() }
            )
        }

        composable(
            route = ManHinh.OnTapFlashcard.route,
            arguments = listOf(
                navArgument(ManHinh.OnTapFlashcard.BO_THE_ID) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val boTheId = backStackEntry.arguments?.getInt(ManHinh.OnTapFlashcard.BO_THE_ID)
                ?.takeIf { it > 0 }
            OnTapFlashcardScreen(
                boTheId = boTheId,
                onQuayLai = { navController.popBackStack() }
            )
        }

        composable(ManHinh.ThongKeHocTap.route) {
            ThongKeHocTapScreen(onQuayLai = { navController.popBackStack() })
        }

        composable(ManHinh.TimKiemTuVung.route) {
            TimKiemTuVungScreen(onQuayLai = { navController.popBackStack() })
        }

        composable(ManHinh.CaiDat.route) {
            CaiDatScreen(onQuayLai = { navController.popBackStack() })
        }

        composable(ManHinh.GioiThieu.route) {
            GioiThieuScreen(onQuayLai = { navController.popBackStack() })
        }
    }
}

