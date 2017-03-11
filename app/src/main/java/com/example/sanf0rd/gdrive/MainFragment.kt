package com.example.sanf0rd.gdrive

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView

/**
 * Created by sanf0rd on 11/03/17.
 */
class MainFragment: Fragment() {

    lateinit var title: String

    val titleTextView: TextView by bindView(R.id.fragment_tittle)

    companion object Factory {
        fun newInstance(title: String): MainFragment {
            val fragment = MainFragment()

            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = arguments.getString("title")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.main_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titleTextView.text = title
    }
}