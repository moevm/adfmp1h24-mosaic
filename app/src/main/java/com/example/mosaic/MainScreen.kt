package com.example.mosaic

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp


@Composable
fun MainScreen (
    mainViewModel: MainViewModel,
    originalBitmap: ArrayList<Bitmap>,
    templateBitmap: ArrayList<Bitmap>,
    onReturn: () -> Unit,
    cols: Int,
    rows: Int
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.TopStart
        ) {
            val density = LocalDensity.current
            val verticalPadding = with(density) { (50.dp - 18.sp.toDp()) / 2 }
            val addedPuzzles = mainViewModel.addedPuzzles
            if (addedPuzzles.size != cols * rows) {
                Text(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(
                            horizontal = 15.dp,
                            vertical = verticalPadding
                        ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = "Счетчик пазлов: ${addedPuzzles.size} / ${cols * rows}"
                )
            } else {
                Text(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(
                            horizontal = 15.dp,
                            vertical = verticalPadding
                        ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    text = "Мозаика собрана!"
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height((screenHeight - 160).dp),
            contentAlignment = Alignment.Center
        ){
             Column (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                val height = (screenHeight - 160).dp
                val width = screenWidth.dp
                val size = if (width / cols > height / rows) height / rows else width / cols
                val addedPuzzles = mainViewModel.addedPuzzles
                for (i in 0..<rows) {
                    Row (
                        modifier = Modifier
                            .width(size * cols)
                            .height(size)
                    ) {
                        for (j in 0..<cols) {
                            if (!addedPuzzles.contains(mainViewModel.field[i][j].id)) {
                                DropItem(
                                    modifier = Modifier,
                                    viewModel = mainViewModel,
                                    puzzleId = mainViewModel.field[i][j].id
                                ) { isInBound, _ ->
                                    Box (
                                        modifier = Modifier
                                            .height(size)
                                            .width(size)
                                            .border(1.dp, color = Color.LightGray)
                                    ) {
                                        Image (
                                            bitmap = templateBitmap[mainViewModel.field[i][j].id].asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier.size(size),
                                            contentScale = ContentScale.FillBounds
                                        )
                                        if (isInBound) {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .background(Color.Black.copy(alpha = 0.25f))
                                            )
                                        }
                                    }
                                }
                            } else {
                                Box (
                                    modifier = Modifier
                                        .height(size)
                                        .width(size)
                                ) {
                                    Image (
                                        bitmap = originalBitmap[mainViewModel.field[i][j].id].asImageBitmap(),
                                        contentDescription = null,
                                        modifier = Modifier.size(size),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        val puzzles = mainViewModel.puzzles
        val addedPuzzles = mainViewModel.addedPuzzles
        if (addedPuzzles.size != cols * rows) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .size((if (addedPuzzles.size < cols * rows - 3) 40 else 0).dp),
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .size((if (addedPuzzles.size < cols * rows - 3) 40 else 0).dp)
                    )
                }
                LazyRow (
                    modifier = Modifier.weight(1f)
                        .padding(
                            vertical = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    items(puzzles) { puzzle ->
                        if (!addedPuzzles.contains(puzzle.id)) {
                            Box (
                                modifier = Modifier
                                    .height(70.dp)
                                    .width((70 + ((screenWidth - 210) / 3)).dp),
                                contentAlignment = Alignment.Center
                            ) {
                                DragTarget (
                                    dataToDrop = puzzle,
                                    viewModel = mainViewModel
                                ) {
                                    Box (
                                        modifier = Modifier
                                            .size(70.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image (
                                            bitmap = originalBitmap[puzzle.id].asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.FillBounds
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                IconButton(
                    modifier = Modifier
                        .size((if (addedPuzzles.size < cols * rows - 3) 40 else 0).dp),
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .size((if (addedPuzzles.size < cols * rows - 3) 40 else 0).dp)
                    )
                }
            }
        } else {
            AlertDialog(
                onDismissRequest = {  },
                title = {
                        Text(
                            text = "Успех!",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                },
                text = {
                    Text(
                        text = "Мозаика собрана!",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                confirmButton = {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onReturn() }
                    ) {
                        Text("Вернуться к выбору изображения")
                    }
                }
            )
        }
    }
}