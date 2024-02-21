package com.example.mosaic

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class ComplexityActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            val original = intent.getIntExtra("original", 0)
            val template = intent.getIntExtra("template", 0)
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
                                text = "Уровень",
                                fontSize = 24.sp,
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
                                text = "Уровень",
                                fontSize = 24.sp,
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
                                text = "сложности",
                                fontSize = 24.sp,
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
                                text = "сложности",
                                fontSize = 24.sp,
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
                                this@ComplexityActivity,
                                LevelActivity::class.java
                            )
                            navigate.putExtra("original", original)
                            navigate.putExtra("template", template)
                            navigate.putExtra("cols", 2)
                            navigate.putExtra("rows", 3)
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Легкий")
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
                                this@ComplexityActivity,
                                LevelActivity::class.java
                            )
                            navigate.putExtra("original", original)
                            navigate.putExtra("template", template)
                            navigate.putExtra("cols", 4)
                            navigate.putExtra("rows", 6)
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Средний")
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
                                this@ComplexityActivity,
                                LevelActivity::class.java
                            )
                            navigate.putExtra("original", original)
                            navigate.putExtra("template", template)
                            navigate.putExtra("cols", 6)
                            navigate.putExtra("rows", 9)
                            startActivity(navigate)
                        }
                    ) {
                        Text(text = "Сложный")
                    }
                }
            }
            Box (
                modifier = Modifier
                    .size(90.dp)
                    .padding(20.dp),

                ) {
                Image (
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(25.dp))
                        .clickable {
                            val navigate = Intent(
                                this@ComplexityActivity,
                                ImageActivity::class.java
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