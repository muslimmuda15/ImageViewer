package com.rachmad.imageviewer.ui.listimage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rachmad.imageviewer.R

import com.rachmad.imageviewer.ui.listimage.ImageItemFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image_item.view.*
import java.io.File

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyImageItemRecyclerViewAdapter(private val mValues: List<String>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MyImageItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mItem = mValues[position]
        Picasso.with(holder.image.context).load("file://" + mValues.get(position)).fit().centerCrop().into(holder.image)
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
