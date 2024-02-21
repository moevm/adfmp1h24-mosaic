package com.example.mosaic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel(cols: Int, rows: Int) : ViewModel() {

    var isCurrentlyDragging by mutableStateOf(false)
        private set

    private val _puzzles = mutableStateListOf<PuzzleUiItem>()
    val puzzles: List<PuzzleUiItem> = _puzzles

    var draggingPuzzle by mutableStateOf<Int?>(null)

    private val _addedPuzzles = mutableStateListOf<Int>()
    val addedPuzzles: List<Int> = _addedPuzzles

    var field by mutableStateOf(emptyList<List<PuzzleUiItem>>())
        private set


    init {
        for (i in 0..<cols*rows) {
            _puzzles.add(PuzzleUiItem(i))
        }
        _puzzles.shuffle()
        for (i in 0..<rows) {
            var tmp = emptyList<PuzzleUiItem>()
            for (j in 0..<cols) {
                tmp = tmp.plus(PuzzleUiItem(i*cols + j))
            }
            field = field.plusElement(tmp)
        }
    }

    fun startDragging() {
//        var tmp = emptyList<PuzzleUiItem>()
//        puzzles.forEach { puzzle ->
//            if (id != puzzle.id) {
//                tmp = tmp.plus(puzzle)
//            }
//        }
//        puzzles = tmp
        // _puzzles.removeIf {puzzle -> puzzle.id == id}
        isCurrentlyDragging = true
    }

    fun stopDragging() {
        isCurrentlyDragging = false
    }

    fun addPuzzle(id: Int) {
        _addedPuzzles.add(id)
    }
}

