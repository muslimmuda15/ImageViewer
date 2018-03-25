package com.rachmad.imageviewer.ui.listimage

import android.app.Activity
import android.graphics.Bitmap
import android.os.AsyncTask
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rachmad.imageviewer.R
import com.rachmad.imageviewer.helper.TargaReader

import com.rachmad.imageviewer.ui.listimage.ImageItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_item.view.*
import java.io.File
import java.io.IOException
import java.net.MalformedURLException

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyImageItemRecyclerViewAdapter(val activity: Activity, private val mValues: List<String>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyImageItemRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageDrawable(holder.image.resources.getDrawable(R.drawable.gradient_text_path))
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
//                Log.d("async", "execute ===> ${mValues.get(position)}")
//                if(fileData == mValues.get(position))
//                    holder.image.setImageBitmap(bitmap)
//            }
//        }

        val tga = TargaReader()

        holder.mItem = mValues[position]
//        val imageWidth = holder.image.width
//        holder.image.layoutParams = ConstraintLayout.LayoutParams(imageWidth, imageWidth)

//        DownloadFilesTask().execute(mValues.get(position))
// ====================================================================================================
        activity.runOnUiThread(object: Runnable{
            override fun run() {
                val bitmap: Bitmap = tga.createTGABitmap(holder.image.context, File(mValues.get(position)))!!
                holder.image.setImageBitmap(bitmap)
            }
        })
// ====================================================================================================
//        Picasso.with(holder.image.context).load("file://" + mValues.get(position)).fit().centerCrop().into(holder.image)
        holder.imagePath.text = File(mValues[position]).name

        holder.mView.setOnClickListener {
            mListener?.onListFragmentInteraction(holder.mItem!!)
        }
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val image: ImageView
        val imagePath: TextView
        var mItem: String? = null

        init {
            image = mView.image as ImageView
            imagePath = mView.image_path as TextView
        }
    }
}
