package com.rachmad.imageviewer.ui.listdir

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rachmad.imageviewer.R
import java.io.File

import com.rachmad.imageviewer.ui.listdir.FileItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
 class MyFileItemRecyclerViewAdapter(private val mValues:List<String>, private val myMap: HashMap<String, ArrayList<String>>, private val mListener:OnListFragmentInteractionListener?):RecyclerView.Adapter<MyFileItemRecyclerViewAdapter.ViewHolder>() {

    public override fun onCreateViewHolder(parent:ViewGroup, viewType:Int): ViewHolder {
        val view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_file_item, parent, false)
        return ViewHolder(view)
    }

    public override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        holder.mItem = mValues.get(position)
//        val bitmap = BitmapFactory.decodeFile(myMap.get(mValues.get(position))!!.get(0))
//        holder.fileDirectory.setImageBitmap(bitmap)
        Picasso.with(holder.fileDirectory.context).load("file://"+myMap.get(mValues.get(position))!!.get(0)).fit().centerCrop().into(holder.fileDirectory)
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
