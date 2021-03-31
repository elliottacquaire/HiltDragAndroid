package com.exae.proandroid.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.exae.proandroid.R
import com.exae.proandroid.base.CoreFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_index.*


@Route(path = "/pos/todo")
@AndroidEntryPoint
class ToDoListFragment : CoreFragment(R.layout.fragment_index) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewPager.adapter = initPageAdapter()
        mViewPager.offscreenPageLimit = 2
        mTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager.currentItem = mTabLayout.selectedTabPosition
            }

        })
        mViewPager.addOnPageChangeListener(object: OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                mTabLayout.selectTab(mTabLayout.getTabAt(position))
//                fromSource = position
            }
        })

        mTabLayout.selectTab(mTabLayout.getTabAt(0))
    }

    private fun initPageAdapter(): PagerAdapter {
        return object :
            FragmentStatePagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return MessageFragment().apply {
//                        arguments = Bundle().apply {
//                            putInt("flowType",productFlowType(position))
//                            putInt("isHistory", HISTORY_ZERO)
//                            putInt("currentStepStatus",FLOW_STATUS_IN_PROCESS)
//                        }
                    }

            }

            override fun getCount(): Int {
                return 4
            }
        }
    }
}