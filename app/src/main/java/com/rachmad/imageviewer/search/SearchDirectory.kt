package com.rachmad.imageviewer.search

import android.util.Log
import java.io.File
import java.io.FilenameFilter
import android.R.attr.name



/**
 * Created by Rachmad on 11/03/2018.
 */
class SearchDirectory{
    lateinit var f: File
    lateinit var listFile: Array<String>
    fun initialFile(path: String){
        f = File(path)
        Log.d("file", "Current dir => ${f.toString()}")
        listFile = f.list(object: FilenameFilter{
            override fun accept(dir: File?, name: String?): Boolean {
                return File(dir, name).isDirectory()
            }
        })
    }
}