package com.example.mosaic

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Tiler {
    companion object {
        fun createBitmaps(
            cols: Int,
            rows: Int,
            resources: Resources,
            original: Int,
            template: Int
        ): Pair<ArrayList<Bitmap>, ArrayList<Bitmap>> {
            val originalBitmap = BitmapFactory.decodeResource(resources, original)
            val templateBitmap = BitmapFactory.decodeResource(resources, template)
            val width = originalBitmap.width / cols
            val height = originalBitmap.height / rows
            val tiledOriginal = ArrayList<Bitmap>()
            val tiledTemplate = ArrayList<Bitmap>()
            for (i in 0..< rows) {
                for (j in 0..< cols) {
                    val x = j * width
                    val y = i * height
                    tiledOriginal.add(Bitmap.createBitmap(originalBitmap, x, y, width, height))
                    tiledTemplate.add(Bitmap.createBitmap(templateBitmap, x, y, width, height))
                }
            }
            return tiledOriginal to tiledTemplate
        }
    }
}