package org.tuwaiq.recipes.view.home.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentPagerAdapter
import org.tuwaiq.recipes.view.home.profile.likes.LikedRecipesFragment


class ProfileVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return MyRecipesFragment()
            1 -> return LikedRecipesFragment()
        }
        return MyRecipesFragment()
    }

}

class MyPagerAdapter(fragmentManager: FragmentManager?) :
    FragmentPagerAdapter(fragmentManager!!) {
    // Returns total number of pages
    override fun getCount(): Int {
        return 2
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return MyRecipesFragment()
            1 -> return LikedRecipesFragment()
        }
        return MyRecipesFragment()
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

}