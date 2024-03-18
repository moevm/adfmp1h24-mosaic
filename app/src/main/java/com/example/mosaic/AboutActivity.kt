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
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            BackHandler {
                val navigate = Intent(
                    this@AboutActivity,
                    MainActivity::class.java
                )
                startActivity(navigate)
            }
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
                            }
                        }
                        Box(modifier = Modifier.height(50.dp))
                        Box (
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            val description = "Приложение создано для тех, " +
                                    "кто ищет спокойствие и релаксацию. " +
                                    "Здесь вы найдете множество красочных " +
                                    "изображений, которые можно собрать в " +
                                    "удивительные мозаичные композиции. " +
                                    "Разнообразные уровни сложности и звуковое " +
                                    "сопровождение помогут вам отдохнуть и насладиться " +
                                    "моментом творчества."
                            Text(
                                text = description,
                                fontSize = 19.sp,
                                textAlign = TextAlign.Justify,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                lineHeight = 28.sp
                            )
                        }
                        Box(modifier = Modifier.height(50.dp))
                        Column (
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box (
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text (
                                    text = "Автор",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        drawStyle = Stroke(
                                            width = 12F
                                        )
                                    )
                                )
                                Text (
                                    text = "Автор",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.White,
                                    style = TextStyle(
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                            Box(modifier = Modifier.height(25.dp))
                            Box (
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text="Студент группы 0303 СПбГЭТУ ЛЭТИ,",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Box(modifier = Modifier.height(10.dp))
                            Box (
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text="Морозов А. Ю.",
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
            Box (
                modifier = Modifier
                    .size(90.dp)
                    .padding(20.dp)
                ) {
                Image (
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .clickable {
                            val navigate = Intent(
                                this@AboutActivity,
                                MainActivity::class.java
                            )
                            startActivity(navigate)
                        },
                    contentScale = ContentScale.FillBounds,
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = null
                )
            }
        }
    }
}