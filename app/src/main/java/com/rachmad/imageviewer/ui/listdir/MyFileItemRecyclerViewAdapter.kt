package com.rachmad.imageviewer.ui.listdir

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rachmad.imageviewer.R
import com.rachmad.imageviewer.helper.TargaReader
import java.io.File

import com.rachmad.imageviewer.ui.listdir.FileItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import android.provider.MediaStore
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
 class MyFileItemRecyclerViewAdapter(val activity: Activity, private val mValues:List<String>, private val myMap: HashMap<String, ArrayList<String>>, private val mListener:OnListFragmentInteractionListener?):RecyclerView.Adapter<MyFileItemRecyclerViewAdapter.ViewHolder>() {
    public override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_file_item, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        holder.fileDirectory.setImageDrawable(holder.fileDirectory.resources.getDrawable(R.drawable.gradient_text_path))
//        class DownloadFilesTask : AsyncTask<String, Void, Bitmap>() {
//            var fileData: String = ""
//            override fun doInBackground(vararg urls: String): Bitmap? {
//                val tga = TargaReader()
//
//                var bitmap: Bitmap? = null
//                try {
//                    fileData = urls[0]
//                    bitmap = tga.createTGABitmap(activity, File(urls[0]))!!
//                } catch (e: MalformedURLException) {
//                    e.printStackTrace()
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//
//                return bitmap
//            }
//
//            override fun onPostExecute(bitmap: Bitmap) {
//                Log.d("async", "background ===> ${myMap.get(mValues.get(position))!!.get(0)}")
//
//                if(fileData == myMap.get(mValues.get(position))!!.get(0))
//                    holder.fileDirectory.setImageBitmap(bitmap)
//            }
//        }

        val tga = TargaReader()
        holder.mItem = mValues.get(position)

//        DownloadFilesTask().execute(myMap.get(mValues.get(position))!!.get(0))
// ====================================================================================================
        activity.runOnUiThread(object: Runnable{
            override fun run() {
                val bitmap: Bitmap = tga.createTGABitmap(activity, File(myMap.get(mValues.get(position))!!.get(0)))!!
                holder.fileDirectory.setImageBitmap(bitmap)
            }
        })
// ====================================================================================================
//        Picasso.with(holder.fileDirectory.context).load("file://"+myMap.get(mValues.get(position))!!.get(0)).fit().centerCrop().into(holder.fileDirectory)
// ====================================================================================================
        holder.pathDirectory.setText(File(mValues.get(position)).name)

        holder.mView.setOnClickListener(object:View.OnClickListener {
            public override fun onClick(v:View) {
                if (null != mListener)
                {
                    mListener!!.onListFragmentInteraction(myMap.get(mValues.get(position))!!, position)
                }
            }
        })
    }

    public override fun getItemCount():Int {
        return mValues.size
    }

    inner class ViewHolder( val mView:View):RecyclerView.ViewHolder(mView) {
        var fileDirectory: ImageView
        var mItem: String? = null
        var pathDirectory: TextView

        init{
            fileDirectory = mView.findViewById<View>(R.id.file_directory) as ImageView
            pathDirectory = mView.findViewById<View>(R.id.path_directory) as TextView
        }
    }
}