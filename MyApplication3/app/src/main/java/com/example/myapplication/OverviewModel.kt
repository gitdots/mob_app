package com.example.myapplication

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class OverviewModel(private val myDate : String) : ViewModel() {

    private val _data = MutableLiveData<MyJsonObject>()
    val data : LiveData<MyJsonObject> = _data

    init {
        getMyObject()
    }

    private fun getMyObject(){
        println(myDate)
        viewModelScope.launch{
            try {
                val result = MarsApi.retrofitService.getObject("predictions", myDate)
                _data.value = result
                println(result.predictii)
            } catch (e: Exception) {
                println(e.message)
            }
        }

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val myDate : String):
        ViewModelProvider.Factory{

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OverviewModel(myDate) as T
        }
    }

}
