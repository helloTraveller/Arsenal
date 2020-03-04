package com.jzy.rand

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jzy.rand.adapters.MY_GARDEN_PAGE_INDEX
import com.jzy.rand.adapters.PLANT_LIST_PAGE_INDEX
import com.jzy.rand.adapters.SunflowerPagerAdapter
import com.jzy.rand.databinding.FragmentViewPagerBinding
import java.lang.IndexOutOfBoundsException

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false);
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = SunflowerPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        return binding.root
    }

    private fun getTabIcon(position: Int) : Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
           MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
           PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
           else -> null
        }
    }

}