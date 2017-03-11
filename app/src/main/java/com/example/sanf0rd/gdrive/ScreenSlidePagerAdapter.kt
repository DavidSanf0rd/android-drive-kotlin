package com.example.sanf0rd.gdrive

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * Created by sanf0rd on 11/03/17.
 */
class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fragment = MainFragment.newInstance(title = "Fragment 1")
    val fragment2 = MainFragment.newInstance(title = "Fragment 2")

    val fragments = arrayOf(fragment, fragment2)

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Fragment 1"
            1 -> return "Fragment 2"
        }

        return "No title"
    }
}