package com.example.sanf0rd.gdrive

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import butterknife.bindView

class MainActivity : AppCompatActivity() {

    val viewPager: ViewPager by bindView(R.id.viewPager)
    var pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = pagerAdapter
    }
}
