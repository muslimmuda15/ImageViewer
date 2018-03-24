package com.rachmad.imageviewer.ui.listdir

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.rachmad.imageviewer.R
import com.rachmad.imageviewer.search.ExternalFound
import com.rachmad.imageviewer.search.SearchFile
import com.rachmad.imageviewer.ui.listimage.ImageListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FileItemFragment.OnListFragmentInteractionListener {
    val searchName by lazy {
        SearchFile()
    }
    val externalFound by lazy {
        ExternalFound()
    }

    var listFile = ArrayList<String>()

    override fun onListFragmentInteraction(item: List<String>, position: Int) {
        val intent = Intent(this, ImageListActivity::class.java)
        intent.putStringArrayListExtra(ImageListActivity.ARG_DIR, ArrayList(item))
        intent.putExtra(ImageListActivity.ARG_POSITION, position)
        startActivity(intent)
    }

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
        Log.d("file", "External Storage Directory => " + externalFound.getExternalDirectory)

        searchName.search(externalFound.getExternalDirectory)
        loading.visibility = LinearLayout.GONE

        val map = searchName.hmap
        Log.d("file", "map : ${map}")
//        listFile = searchName.listFile
//        listFile.forEach {
//            Log.d("file", "my file -> " + it.toString())
//        }

        val fragment = FileItemFragment.newInstance(map)
        supportFragmentManager.beginTransaction().add(R.id.directoryList, fragment).commit()
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
