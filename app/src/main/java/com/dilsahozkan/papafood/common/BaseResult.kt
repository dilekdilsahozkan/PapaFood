package com.dilsahozkan.papafood.common

sealed class BaseResult <out T> {
    data class Success <T: Any>(val data : T) : BaseResult<T>()
    data class Error(val message: String): BaseResult<Nothing>()
    data object Loading: BaseResult<Nothing>()
}