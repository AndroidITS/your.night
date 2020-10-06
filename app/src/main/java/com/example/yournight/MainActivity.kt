package com.example.yournight

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tabLayout = findViewById<TabLayout>(R.id.tabs)

        val customPageAdapter = CustomPageAdapter(supportFragmentManager, 1)
        viewPager.setAdapter(customPageAdapter)

        //TAB:

        //TAB:
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24)
        tabLayout.getTabAt(2)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_transform_24)
        tabLayout.getTabAt(3)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_settings_24)
    }
}