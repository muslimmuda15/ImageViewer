package com.rachmad.imageviewer.search

import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED_READ_ONLY
import android.os.Environment.MEDIA_MOUNTED



/**
 * Created by Rachmad on 11/03/2018.
 */
class ExternalFound{
    var mExternalStorageAvailable = false
    var mExternalStorageWriteable = false

    var getExternalDirectory = ""
    var getExternalDataDirectory = ""
    val state = Environment.getExternalStorageState()

    fun checkExternal(){
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageWriteable = true
            mExternalStorageAvailable = mExternalStorageWriteable
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true
            mExternalStorageWriteable = false
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageWriteable = false
            mExternalStorageAvailable = mExternalStorageWriteable
        }

        if(mExternalStorageAvailable) {
            getExternalDirectory = Environment.getExternalStorageDirectory().toString()
            getExternalDataDirectory = Environment.getDataDirectory().toString()
        }
    }
}