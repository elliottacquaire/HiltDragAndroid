package com.exae.proandroid.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter
import com.exae.proandroid.R
import com.exae.proandroid.base.CoreFragment
import com.exae.proandroid.base.handleResponse
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_test_drive.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject


/**
 * 试驾列表
 */

@Route(path = "/pos/testDrive")
@AndroidEntryPoint
class TestDriveFragment : CoreFragment(R.layout.fragment_test_drive) {


//    private val viewModel: PosTestDriveModel by viewModels()

//    @Inject
//    lateinit var listAdapter: PosTestDriveAdapter

//    private val listNode: ArrayList<TestDriveEntity> = ArrayList()

    private var isHistory = 0 //历史
    private var status = -1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        roleViewModel = viewModel

        isHistory = arguments?.getInt("isHistory",0)?:0
        status = arguments?.getInt("status")?:-1

//        EventBus.getDefault().register(this)

        smartRefreshLayout.setRefreshHeader(ClassicsHeader(context))

        mListView.layoutManager = LinearLayoutManager(context)

//        mListView.adapter = listAdapter
        //下拉刷新
        smartRefreshLayout.setOnRefreshListener {
//            requestNetData()
            smartRefreshLayout.finishRefresh(true)
        }
        emptyView.setOnClickListener {
//            requestNetData()
        }

//        viewModel.listResponse.observe(this, Observer { resources ->
//            handleResponse(resources) {
//                if (it.data != null && !it.data.isNullOrEmpty()) {
//                    getData(it.data!!)
//                    listAdapter.data.clear()
//                    listAdapter.data.addAll(listNode)
//                    listAdapter.notifyDataSetChanged()
//                    listAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom)
//                    emptyView.visibility = View.GONE
//                    smartRefreshLayout.visibility = View.VISIBLE
//                } else {
//                    emptyView.visibility = View.VISIBLE
//                    smartRefreshLayout.visibility = View.GONE
//                }
//            }
//
//        })

//        when (viewModel.getRolePermission()) {
//            0 -> viewModel.requestDriveRequest()
//        }

    }

//    private fun getData(listData: List<TestDriveListModel>) {
//        listNode.clear()
//        for (itemData in listData) {
//            val data = TestDriveEntity(1)
//            data.groupName = itemData.groupName?:""
//            listNode.add(data)
//            if (itemData != null && itemData.driveResources!!.isNotEmpty()) {
//                for ((index, secondItem) in itemData.driveResources!!.withIndex()) {
//                    val data = TestDriveEntity(2)
//                    data.driveResources = secondItem
//                    data.status = itemData.status
//                    data.isHistory = isHistory
//                    listNode.add(data)
//                    if (index == itemData.driveResources!!.size - 1) {
//                        val data = TestDriveEntity(3)
//                        listNode.add(data)
//                    }
//                }
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
//        requestNetData()
    }

    //刷新data
//    private fun requestNetData() {
//        when(isHistory){
//            1 -> {
//                viewModel.state.requestHistory.status = status
//                viewModel.requestTestDriveHistoryList()
//            }
//            else ->  viewModel.requestTestDriveList()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
//        EventBus.getDefault().unregister(this)
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onMessageEvent(event: MessageEvent?) {
////        requestNetData()
//    }
}