package com.kaliostech.proview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaliostech.proview.data.repository.PdfRepository
import com.kaliostech.proview.presentation.model.PdfFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val pdfRepository: PdfRepository
) : ViewModel() {
    
    private val _pdfFiles = MutableStateFlow<List<PdfFile>>(emptyList())
    val pdfFiles: StateFlow<List<PdfFile>> = _pdfFiles
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    
    fun loadPdfFiles() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            pdfRepository.getAllPdfFiles()
                .catch { e ->
                    _error.value = e.message
                    _isLoading.value = false
                }
                .collect { files ->
                    _pdfFiles.value = files
                    _isLoading.value = false
                }
        }
    }
} 