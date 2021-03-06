package com.rachmad.imageviewer.ui.listdir

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rachmad.imageviewer.R

/**
 * A fragment representing a list of Items.
 *
 *
 * Activities containing this fragment MUST implement the [OnListFragmentInteractionListener]
 * interface.
 */
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
class FileItemFragment : Fragment() {
    // TODO: Customize parameters
    private lateinit var myMap: HashMap<String, ArrayList<String>>
    private var mListener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            myMap = arguments.getSerializable(ARG_MAP) as HashMap<String, ArrayList<String>>
        }
        Log.d("file", "map fragment : $myMap")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_file_item_list, container, false)
        Log.d("file", "through fragment")

        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
//            if (mColumnCount <= 1) {
                view.layoutManager = LinearLayoutManager(context)
//            } else {
//                view.layoutManager = GridLayoutManager(context, 1)
//            }
            Log.d("file", "count file : ${ArrayList(myMap.keys).size}")
            val adapter = MyFileItemRecyclerViewAdapter(activity, ArrayList(myMap.keys), myMap, mListener)
//            val adapter = MyFileItemRecyclerViewAdapter(ArrayList(myMap.keys), mListener)
            adapter.notifyDataSetChanged()
            // the lsit of restaurant, get from MenuListModel
            view.adapter = adapter
        }
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: List<String>, position: Int)
    }

    companion object {

        // TODO: Customize parameter argument names
        private val ARG_MAP = "map"

        // TODO: Customize parameter initialization
        fun newInstance(map: HashMap<String, ArrayList<String>>): FileItemFragment {
            val fragment = FileItemFragment()
            val args = Bundle()
//            args.putInt(ARG_COLUMN_COUNT, 1)
            args.putSerializable(ARG_MAP, map)
            fragment.arguments = args
            return fragment
        }
    }
}
