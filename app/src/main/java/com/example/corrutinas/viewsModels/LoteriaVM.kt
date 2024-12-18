package com.example.corrutinas.viewsModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoteriaVM: ViewModel() {
    private val _lotNumbers = mutableStateOf( emptyList<Int>() )
    val lotNumbers: State<List<Int>> = _lotNumbers

    var _isLoading = mutableStateOf(false)
    var finish = mutableStateOf(false)
    fun autogenerateN(){
        try {
            viewModelScope.launch {
                generaLotNumbers()
            }
        }catch (e: Exception){
            println(e.toString())
        }finally {
            finish.value = true
        }
    }

    private suspend fun generaLotNumbers()
    {
        _isLoading.value = true
        for(i in 0 until 7){
            delay(1000)
            val number = Random.nextInt(70)+1
            val newList = _lotNumbers.value.toMutableList()
            newList.add(number)
            _lotNumbers.value = newList
        }
        _isLoading.value = false
    }


}