package com.exae.proandroid.fragment

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.exae.proandroid.R
import com.exae.proandroid.base.CoreFragment
import dagger.hilt.android.AndroidEntryPoint

@Route(path = "/pos/mine")
@AndroidEntryPoint
class MyProfileFragment : CoreFragment(R.layout.fragment_my_profile) ,View.OnClickListener{

//    private val viewModel: PosMyProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.userInfoReqponse.observe(viewLifecycleOwner, Observer { resources ->
//            handleResponse(resources,
//                successHandler = {
//                    mFullName.text = it.data?.userName ?: ""
//                    mPosition.text = "${it.data?.dealerName ?: ""} ${it.data?.roleName ?: ""}"
//                },
//                failedHandler = {
//                    ToastUtil.showCenter(CommonUtils.requestTips(it))
//                })
//        })

//        myApprovalPrice.setOnClickListener(this)
//        myApprovalContract.setOnClickListener(this)
//        lin_persistence.setOnClickListener(this)
//        lin_test_drive.setOnClickListener(this)
//        lin_test_record.setOnClickListener(this)


//        when(viewModel.getRolePermission()){
//            0 -> {
//                lin_cus.visibility = View.VISIBLE
//                lin_manage.visibility = View.VISIBLE
//            }
//            2 -> {
//                lin_cus.visibility = View.VISIBLE
//                lin_manage.visibility = View.GONE
//            }
//            else -> {
//                lin_cus.visibility = View.GONE
//                lin_manage.visibility = View.VISIBLE
//            }
//        }

    }

    override fun onResume() {
        super.onResume()
//        viewModel.request()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.myApprovalPrice -> {
                ARouter.getInstance().build("/pos/history/record")
                    .withInt("clickType", 1)  //报价单
                    .navigation(activity)
            }
            R.id.myApprovalContract -> {
                ARouter.getInstance().build("/pos/history/record")
                    .withInt("clickType", 2) //合同
                    .navigation(activity)
            }
            R.id.lin_persistence -> {
                ARouter.getInstance().build("/pos/history/record")
                    .withInt("clickType", 6) //暂留
                    .navigation(activity)
            }
            R.id.lin_test_drive -> {
                ARouter.getInstance().build("/pos/history/record")
                    .withInt("clickType",101) //试驾历史
                    .navigation(activity)
            }
            R.id.lin_test_record -> {
                ARouter.getInstance().build("/pos/drive/route")
                    .withInt("clickType",101) //试驾路线录制
                    .navigation(activity)
            }
        }
    }
}