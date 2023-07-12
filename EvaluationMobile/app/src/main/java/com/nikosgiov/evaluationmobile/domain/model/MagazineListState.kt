package com.nikosgiov.evaluationmobile.domain.model

data class MagazineListState(
    val isLoading: Boolean = false,
    val magazines: List<Magazine> = emptyList(),
    val error: String = ""

)
