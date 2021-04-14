package com.exae.proandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.launcher.ARouter
import com.exae.proandroid.adapter.PosTransferAdapter
import com.exae.proandroid.base.PosBaseActivity
import com.exae.proandroid.base.handleResponse
import com.exae.proandroid.bean.TestBean
import com.exae.proandroid.bean.TestData
import com.exae.proandroid.viewmodel.TestViewModel
import com.google.android.material.tabs.TabLayout
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : PosBaseActivity(R.layout.activity_main) {

    @Inject
    lateinit var testData: TestData

    @Inject
    lateinit var testBean: TestBean

    private val viewModel: TestViewModel by viewModels()

    private var userPermission = 0

    //由 Hilt 注入的字段不能为私有字段。尝试使用 Hilt 注入私有字段会导致编译错误。
    @Inject
    lateinit var posTransferAdapter: PosTransferAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBackState(false)

        Logger.d(testData.toString())

        Logger.d(testBean.toString())

        viewModel.test()

//        click.setOnClickListener {
            viewModel.codeLoginRequest("13811112222","666666")
//        }


        viewModel.loginCodeResponse.observe(this, Observer {
            handleResponse(it) {
                Logger.d(it)
            }
        })


        when (userPermission) {
            2 -> {
//                title = getString(R.string.tab_to_task)
//                imgResourceId = R.drawable.tab_icon_message_drawable
                setToolTitle(getString(R.string.title_to_test_drive))
                mTabLayout.addTab(
                    mTabLayout.newTab().setCustomView(
                        getTabView(
                            getString(R.string.tab_drive),
                            R.drawable.tab_icon_message_drawable
                        )
                    )
                )
            }
            0 -> { //add 顾问，经理同时存在权限审批
//                title = getString(R.string.tab_to_task)
//                imgResourceId = R.drawable.tab_icon_message_drawable
                setToolTitle(getString(R.string.title_to_do))
                mTabLayout.addTab(
                    mTabLayout.newTab().setCustomView(
                        getTabView(
                            getString(R.string.tab_to_do),
                            R.drawable.tab_icon_index_drawable
                        )
                    )
                )
                mTabLayout.addTab(
                    mTabLayout.newTab().setCustomView(
                        getTabView(
                            getString(R.string.tab_drive),
                            R.drawable.tab_icon_message_drawable
                        )
                    )
                )
            }
            else -> {
//                title = getString(R.string.tab_message)
//                imgResourceId = R.drawable.tab_icon_message_drawable
                setToolTitle(getString(R.string.title_to_do))
                mTabLayout.addTab(
                    mTabLayout.newTab().setCustomView(
                        getTabView(
                            getString(R.string.tab_to_do),
                            R.drawable.tab_icon_index_drawable
                        )
                    )
                )
            }
        }


        mTabLayout.addTab(
            mTabLayout.newTab().setCustomView(
                getTabView(
                    getString(R.string.tab_my_profile),
                    R.drawable.tab_icon_profile_drawable
                )
            )
        )

        mViewPager.adapter = initPageAdapter()
        mViewPager.offscreenPageLimit = 2

        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                mViewPager.currentItem = mTabLayout.selectedTabPosition
            }

        })

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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
                setToolTitle(mViewPager.adapter?.getPageTitle(position).toString())
                when(userPermission){
                    0 -> {
                        if (position == 2) {
                            setSettingImage(true)
                        } else {
                            setSettingImage(false)
                        }
                    }
                    else -> {
                        if (position == 1) {
                            setSettingImage(true)
                        } else {
                            setSettingImage(false)
                        }
                    }
                }

            }

        })

    }


    override fun rightImageClick() {
        super.rightImageClick()
        ARouter.getInstance().build("/pos/setting").navigation(this)
    }

    private fun initPageAdapter(): PagerAdapter {
        return object : FragmentStatePagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment {
                when(userPermission){  // 0,全部；1，展示审批相关功能 2，展示试驾相关功能
                    0 -> {
                        return when (position) {
                            0 -> ARouter.getInstance().build("/pos/todo").navigation() as Fragment
                            1 -> {
                                val arguments = Bundle().apply {
                                    putInt("isHistory", 0)
                                }

                                ARouter.getInstance().build("/pos/testDrive").with(arguments).navigation() as Fragment
                            }
                            else -> ARouter.getInstance().build("/pos/mine").navigation() as Fragment
//                            0 -> ToDoListFragment()
//                            1 -> TestDriveFragment().apply {
//                                arguments = Bundle().apply {
//                                    putInt("isHistory", HISTORY_ZERO)
//                                }
//                            }
//                            else -> MyProfileFragment()
                        }
                    }
                    2 -> {
                        return when (position) {
                            0 -> {
                                val arguments = Bundle().apply {
                                    putInt("isHistory", 0)
                                }

                                ARouter.getInstance().build("/pos/testDrive").with(arguments).navigation() as Fragment
                            }
                            else -> ARouter.getInstance().build("/pos/mine").navigation() as Fragment
//                            0 -> TestDriveFragment().apply {
//                                arguments = Bundle().apply {
//                                    putInt("isHistory", HISTORY_ZERO)
//                                }
//                            }
//                            else -> MyProfileFragment()
                        }
                    }
                    else -> {
                        return when (position) {
                            0 -> ARouter.getInstance().build("/pos/todo").navigation() as Fragment
                            else -> ARouter.getInstance().build("/pos/mine").navigation() as Fragment
//                            0 -> ToDoListFragment()
//                            else -> MyProfileFragment()
                        }
                    }
                }

            }

            override fun getCount(): Int {
                return when(userPermission){
                    0 -> {
                        3
                    }
                    else -> {
                        2
                    }
                }

            }

            override fun getPageTitle(position: Int): CharSequence? {
                when(userPermission){  // 0,全部；1，展示审批相关功能 2，展示试驾相关功能
                    0 -> {
                        return when (position) {
                            0 -> resources.getString(R.string.title_to_do)
                            1 -> resources.getString(R.string.title_to_test_drive)
                            else -> resources.getString(R.string.tab_my_profile)
                        }
                    }
                    2 -> {
                        return when (position) {
                            0 -> resources.getString(R.string.title_to_test_drive)
                            else -> resources.getString(R.string.tab_my_profile)
                        }
                    }
                    else -> {
                        return when (position) {
                            0 -> resources.getString(R.string.title_to_do)
                            else -> resources.getString(R.string.tab_my_profile)
                        }
                    }
                }

            }

            override fun finishUpdate(container: ViewGroup) {
                try {
                    super.finishUpdate(container)
                }catch (e : NullPointerException){}
            }
        }
    }

    // Tab自定义view
    private fun getTabView(title: String?, image_src: Int): View? {
        val v: View =
            LayoutInflater.from(this).inflate(R.layout.tab_item_view, null)
        val textView = v.findViewById(R.id.tv_title) as TextView
        textView.text = title?:""
        val imageView: ImageView = v.findViewById(R.id.img_view) as ImageView
        imageView.setImageResource(image_src)
        return v
    }


}