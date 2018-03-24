package com.rachmad.imageviewer.ui.listimage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rachmad.imageviewer.R
import kotlinx.android.synthetic.main.activity_image_list.imageList

class ImageListActivity : AppCompatActivity(), ImageItemFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: String) {

    }

    lateinit var listData: List<String>
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)

        listData = intent.getStringArrayListExtra(ARG_DIR)
        position = intent.getIntExtra(ARG_POSITION, 0)

        val fragment = ImageItemFragment.newInstance(3, position, listData)
        supportFragmentManager.beginTransaction().add(R.id.imageList, fragment).commit()
    }

    companion object {
        val ARG_DIR = "dir"
        val ARG_POSITION = "position"
    }
}
