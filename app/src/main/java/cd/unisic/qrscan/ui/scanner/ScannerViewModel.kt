package cd.unisic.qrscan.ui.scanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cd.unisic.qrscan.data.repository.StudentRepository
import cd.unisic.qrscan.domain.model.Student
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ScannerUiState {
    object Idle : ScannerUiState()
    object Loading : ScannerUiState()
    data class Success(val student: Student) : ScannerUiState()
    data class Error(val message: String) : ScannerUiState()
}

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val repository: StudentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScannerUiState>(ScannerUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onScanResult(hash: String) {
        if (_uiState.value is ScannerUiState.Loading) return
        
        _uiState.value = ScannerUiState.Loading
        viewModelScope.launch {
            repository.getStudentInfo(hash)
                .onSuccess { student ->
                    _uiState.value = ScannerUiState.Success(student)
                }
                .onFailure { error ->
                    _uiState.value = ScannerUiState.Error(error.message ?: "Erreur inconnue")
                }
        }
    }

    fun resetState() {
        _uiState.value = ScannerUiState.Idle
    }
}
