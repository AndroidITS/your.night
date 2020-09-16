package com.example.yournight

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class CustomPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = HomePageFragment()
        when (position) {
            0 -> fragment = HomePageFragment()
            1 -> fragment = FavouritesFragment()
            2 -> fragment = RandomFragment()
            3 -> fragment = SettingsFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title = "Home"
            1 -> title = "Favourite"
            2 -> title = "Random"
            3 -> title = "Settings"
        }
        return title
    }
}