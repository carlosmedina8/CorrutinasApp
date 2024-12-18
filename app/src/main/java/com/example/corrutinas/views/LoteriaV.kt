package com.example.corrutinas.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.corrutinas.viewsModels.LoteriaVM

@Composable
fun LoteriaV(viewModels: LoteriaVM){
    val lottonNumbers = viewModels.lotNumbers.value
    val isLoading = viewModels._isLoading.value
    val isFinished = viewModels.finish.value

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (lottonNumbers.isEmpty()){
            Text(text = "Loteria",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            LotteryNumbers(lottonNumbers)
        }
        Button(onClick = { viewModels.autogenerateN()
            println(isLoading.toString())}) {
            if(isLoading){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Generando",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(12.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.size(15.dp),
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                }
            }
            else{
                if(!isFinished){
                    Text(text = "Generar",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                }
                else{
                    Text(text = "¡Finalizado!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }

    }
}

@Composable
fun LotteryNumbers(lottonNumbers: List<Int>){
    LazyRow (
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ){
        items(lottonNumbers) { number ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(48.dp)
                    .background(Color.Red, CircleShape)
            ){
                Text(
                    text = number.toString(),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}