package com.example.mosaic

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(0.8f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box (
                    modifier = Modifier
                        .padding(
                            horizontal = 20.dp
                        )
                ) {
                    Column {
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text (
                                text = "Умиротворяющие",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    drawStyle = Stroke(
                                        width = 16F
                                    )
                                )
                            )
                            Text (
                                text = "Умиротворяющие",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 7.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text (
                                text = "мозаики",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.Black,
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    drawStyle = Stroke(
                                        width = 16F
                                    )
                                )
                            )
                            Text (
                                text = "мозаики",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                style = TextStyle(
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                        Box(modifier = Modifier.height(140.dp))
                    }
                }
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                        .padding(10.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            val navigate = Intent(
                                this@MainActivity,
                                ImageActivity::class.java
                            )
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Играть")
                    }
                }
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                        .padding(10.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            val navigate = Intent(
                                this@MainActivity,
                                AboutActivity::class.java
                            )
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Описание")
                    }
                }
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                        .padding(10.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            finishAffinity()
                        }
                    ) {
                        Text(text = "Выход")
                    }
                }
            }
        }
    }
}