package com.nikosgiov.evaluationmobile.presentation.magazines

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikosgiov.evaluationmobile.common.Resource
import com.nikosgiov.evaluationmobile.domain.model.Magazine
import com.nikosgiov.evaluationmobile.domain.usecase.FetchMagazinesUseCase
import kotlinx.coroutines.launch

class MagazineViewModel(
    private val fetchMagazinesUseCase: FetchMagazinesUseCase
) : ViewModel() {
    private val magazineListState = MutableLiveData<Resource<List<Magazine>>>()
    val magazineListLiveData: LiveData<Resource<List<Magazine>>>
        get() = magazineListState

    fun fetchMagazines(accessToken: String, tokenType: String) {
        viewModelScope.launch {
            try {
                magazineListState.value = Resource.Loading()
                val result = fetchMagazinesUseCase.fetchMagazines(accessToken, tokenType)
                magazineListState.value = result

                if (result is Resource.Success) {
                    Log.d("API Response", result.data.toString())
                } else if (result is Resource.Error) {
                    Log.e("API Error", "Error occurred: ${result.message}")
                }
            } catch (e: Exception) {
                val errorMessage = e.message ?: "Unknown error"
                magazineListState.value = Resource.Error(errorMessage)
                Log.e("API Error", "Error occurred: $errorMessage", e)
            }
        }
    }
}
