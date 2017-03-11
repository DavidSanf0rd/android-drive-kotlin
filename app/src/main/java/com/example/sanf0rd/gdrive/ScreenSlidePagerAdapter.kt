package com.example.sanf0rd.gdrive

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by sanf0rd on 11/03/17.
 */
class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragment = MainFragment()

    override fun getItem(position: Int): Fragment {
        return fragment
    }

    override fun getCount(): Int {
        return 1
    }
}