package com.rachmad.imageviewer.helper

import android.content.Context
import android.graphics.Bitmap
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.util.Log
import java.io.File
import java.io.FileInputStream


/**
 * Created by Rachmad on 20/03/2018.
 */
class TargaReader{
    fun createTGABitmap(context: Context, path: File): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            if(path.exists()) {
                Log.d("file", "Check Image : ${path.name}")
                val file = FileInputStream(path)
                val buffer = ByteArray(file.available())
                file.read(buffer)
                file.close()

                val pixels = TGAReader.read(buffer, TGAReader.ARGB)
                val width = TGAReader.getWidth(buffer)
                val height = TGAReader.getHeight(buffer)

                bitmap = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.ARGB_8888)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return bitmap
    }
}