package com.rachmad.imageviewer.helper

import android.content.Context
import android.graphics.Bitmap
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth



/**
 * Created by Rachmad on 20/03/2018.
 */
class TargaReader{
    private fun createTGABitmap(context: Context, path: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val `is` = context.getAssets().open(path)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()

            val pixels = TGAReader.read(buffer, TGAReader.ARGB)
            val width = TGAReader.getWidth(buffer)
            val height = TGAReader.getHeight(buffer)

            bitmap = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.ARGB_8888)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }
}