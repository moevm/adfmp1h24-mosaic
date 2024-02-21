package com.example.mosaic

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

internal var LocalDragTargetInfo = compositionLocalOf { DragTargetInfo() }

@Composable
fun DragTarget(
    modifier: Modifier = Modifier,
    dataToDrop: PuzzleUiItem,
    viewModel: MainViewModel,
    content: @Composable (()->Unit)
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDragTargetInfo.current

    Box (
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                detectDragGestures (
                    onDragStart = {
                        viewModel.startDragging()
                        currentState.dataToDrop = dataToDrop
                        currentState.isDragging = true
                        viewModel.draggingPuzzle = dataToDrop.id
                        currentState.dragPosition = currentPosition + it
                        currentState.draggableComposable = content
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        currentState.dragOffset += Offset(dragAmount.x, dragAmount.y)
                    },
                    onDragEnd = {
                        viewModel.stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                        viewModel.draggingPuzzle = null
                    },
                    onDragCancel = {
                        viewModel.stopDragging()
                        currentState.dragOffset = Offset.Zero
                        currentState.isDragging = false
                        viewModel.draggingPuzzle = null
                    }
                )
            }
    ) {
        if (dataToDrop.id != viewModel.draggingPuzzle) {
            content()
        }
    }
}

@Composable
fun DropItem (
    modifier: Modifier,
    viewModel: MainViewModel,
    puzzleId: Int,
    content: @Composable (BoxScope.(isInBound: Boolean, data: PuzzleUiItem?) -> Unit)
) {
    val dragInfo = LocalDragTargetInfo.current
    val dragPosition = dragInfo.dragPosition
    val dragOffset = dragInfo.dragOffset
    var isCurrentDropTarget by remember {
        mutableStateOf(false)
    }

    Box (
        modifier = modifier
            .onGloballyPositioned {
                it.boundsInWindow().let { rect ->
                    isCurrentDropTarget = rect.contains(dragPosition + dragOffset)
                }
            }
    ) {
        val data = if (isCurrentDropTarget && !dragInfo.isDragging) dragInfo.dataToDrop else null
        if (isCurrentDropTarget && !dragInfo.isDragging) {
            if (puzzleId == dragInfo.dataToDrop?.id) {
                viewModel.addPuzzle(puzzleId)
            }
        }
        content(isCurrentDropTarget, data)
    }
}

@Composable
fun DraggableScreen (
    modifier: Modifier = Modifier,
    cols: Int,
    rows: Int,
    content: @Composable BoxScope.() -> Unit
) {
    val state = remember {
        DragTargetInfo()
    }

    CompositionLocalProvider (
        LocalDragTargetInfo provides state
    ) {
        Box (
            modifier = modifier.fillMaxSize()
        ) {
            content()
            if (state.isDragging) {
                var targetSize by remember {
                    mutableStateOf(IntSize.Zero)
                }
                val screenWidth = LocalConfiguration.current.screenWidthDp
                val screenHeight = LocalConfiguration.current.screenHeightDp
                val height = (screenHeight - 160).dp
                val width = screenWidth.dp
                val squareSize = if (width / cols > height / rows) height / rows else width / cols
                Box (
                    modifier = Modifier
                        .graphicsLayer {
                            val offset = (state.dragPosition + state.dragOffset)
                            scaleX = squareSize.value / 70
                            scaleY = squareSize.value / 70
                            alpha = if (targetSize == IntSize.Zero) 0f else 1f
                            translationX = offset.x.minus(targetSize.width / 2)
                            translationY = offset.y.minus(targetSize.height / 2)
                        }
                        .onGloballyPositioned {
                            targetSize = it.size
                        }
                ) {
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}

internal  class  DragTargetInfo {
    var isDragging by mutableStateOf(false)
    var dragPosition by mutableStateOf(Offset.Zero)
    var dragOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable () -> Unit)?>(null)
    var dataToDrop by mutableStateOf<PuzzleUiItem?>(null)
}