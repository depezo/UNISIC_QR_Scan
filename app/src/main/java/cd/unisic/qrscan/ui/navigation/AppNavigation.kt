package cd.unisic.qrscan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import cd.unisic.qrscan.ui.detail.StudentDetailScreen
import cd.unisic.qrscan.ui.home.HomeScreen
import cd.unisic.qrscan.ui.scanner.ScannerScreen
import cd.unisic.qrscan.ui.scanner.ScannerUiState
import cd.unisic.qrscan.ui.scanner.ScannerViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val scannerViewModel: ScannerViewModel = hiltViewModel()
    val uiState by scannerViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(onScanClick = {
                scannerViewModel.resetState()
                navController.navigate("scanner")
            })
        }
        
        composable("scanner") {
            // Observe state within scanner screen or use navigation
            LaunchedEffect(uiState) {
                if (uiState is ScannerUiState.Success) {
                    navController.navigate("detail") {
                        popUpTo("home")
                    }
                }
            }

            ScannerScreen(
                uiState = uiState,
                onScanResult = { hash ->
                    scannerViewModel.onScanResult(hash)
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("detail") {
            val state = uiState
            if (state is ScannerUiState.Success) {
                StudentDetailScreen(
                    student = state.student,
                    onBack = {
                        scannerViewModel.resetState()
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
