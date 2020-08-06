package com.example.edamamapp.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.edamamapp.IngredientsFragment
import com.example.edamamapp.InfoFragment
import com.example.edamamapp.R

class RecipeDetailsViewPager(fragmentManager: FragmentManager, private val context: Context) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val NUM_PAGES = 2;
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> InfoFragment()
            1 -> IngredientsFragment()
            else -> InfoFragment()
        }
    }

    override fun getCount(): Int = NUM_PAGES

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> context.resources.getString(R.string.info)
            1 -> context.resources.getString(R.string.ingredients)
            else -> context.resources.getString(R.string.ingredients)
        }
    }
}