package com.rachmad.imageviewer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.rachmad.imageviewer.search.ExternalFound
import com.rachmad.imageviewer.search.SearchDirectory

class MainActivity : AppCompatActivity() {
    val searchName by lazy {
        SearchDirectory()
    }
    val externalFound by lazy {
        ExternalFound()
    }

    lateinit var listFile: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()
    }

    val PERMISSION_REQUEST = 4312
    fun checkPermission(){
        // TODO - Request permission

//        val permissions = arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_COARSE_LOCATION)
        val permissions = arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(!hasPermission(this, permissions)){
//            val restaurantFragment: Fragment = RestaurantItemFragment()
//            supportFragmentManager.beginTransaction().add(R.id.layout_fragment, restaurantFragment).commit()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(permissions, PERMISSION_REQUEST)
            }
        }
        else{
            // Set the adapter
            processFile()
        }

    }

    fun hasPermission(context: Context, permissions: Array<String>): Boolean{
        for(permission in permissions){
            if(ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }

    private fun processFile(){
        externalFound.checkExternal()
//        Log.d("file", "External available => " + externalFound.mExternalStorageAvailable)
        Log.d("file", "External Storage Directory => " + externalFound.getExternalDirectory)
//        Log.d("file", "External Storage Data Directory => " + externalFound.getExternalDataDirectory)

        searchName.initialFile(externalFound.getExternalDirectory)
        listFile = searchName.listFile
        listFile.forEach {
            Log.d("file", "my file -> " + it.toString())
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            PERMISSION_REQUEST -> {
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    processFile()
                }
                else{
//                    processFile()
                    Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
