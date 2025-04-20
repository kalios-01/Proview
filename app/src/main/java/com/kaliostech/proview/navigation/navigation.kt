package com.kaliostech.proview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kaliostech.proview.presentation.screens.main.MainScreen
import com.kaliostech.proview.presentation.screens.pdf.PdfListScreen
import com.kaliostech.proview.presentation.screens.splashscreen.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("main") { MainScreen() }
        // Keep the individual screen routes for potential deep linking
        composable("pdf_list") { PdfListScreen() }
    }
}
