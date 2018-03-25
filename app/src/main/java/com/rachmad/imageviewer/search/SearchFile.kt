package com.rachmad.imageviewer.search

import android.util.Log
import com.rachmad.imageviewer.data.FileData
import java.io.File
import java.nio.file.Files.isDirectory
import java.util.regex.Pattern


/**
 * Created by Rachmad on 12/03/2018.
 */
class SearchFile{
    var listFile = ArrayList<String>()

    var dirData = ArrayList<FileData>()
    var hmap = HashMap<String, ArrayList<String>>()

    fun search(directoryName: String) {
        val directory = File(directoryName)

        // get all the files from a directory
        val fList = directory.listFiles()
        for (file in fList) {
            if(file.isFile()){
//                Log.d("file", "my File file -> " + file.name)
                if (Pattern.compile("\\.tga$", Pattern.CASE_INSENSITIVE).matcher(file.name).find()) {
//                    Log.d("file", "my JPG file -> " + file.toString())
    //                listFile.add(file.toString())
                    if (hmap.isEmpty()) {
                        val data = ArrayList<String>()
                        data.add(file.toString())
                        hmap.put(file.parentFile.absolutePath.toString(), data)
                    } else {
                        if (hmap.get(file.parentFile.absolutePath.toString()) != null) {
                            var data: ArrayList<String> = hmap.get(file.parentFile.absolutePath.toString())!!
                            data.add(file.toString())
                            hmap.put(file.parentFile.absolutePath.toString(), data)
                        } else {
                            val data = ArrayList<String>()
                            data.add(file.toString())
                            hmap.put(file.parentFile.absolutePath.toString(), data)
                        }
                    }
                }
            } else if (file.isDirectory()) {
//                Log.d("file", "my DIRECTORY file -> " + file.toString())
                search(file.getAbsolutePath())
            }
            else{
//                Log.d("file", "my else file -> " + file.toString())
            }
        }
    }
}