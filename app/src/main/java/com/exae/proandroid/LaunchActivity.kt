package com.exae.proandroid

import android.content.Intent
import android.os.Bundle
import com.exae.proandroid.base.PosBaseActivity
import com.exae.proandroid.bean.TestBean
import com.exae.proandroid.bean.TestData
import com.exae.proandroid.utils.CommonUtils
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_launch.*
import javax.inject.Inject

@AndroidEntryPoint
class LaunchActivity : PosBaseActivity(R.layout.activity_launch) {

    @Inject
    lateinit var testBean: TestBean


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Logger.d(testBean.toString())

        butions.setOnClickListener {
//            startActivity(Intent(this,MainActivity::class.java))
//            CommonUtils.tt()
            CommonUtils.apply_Let_With_Run()
        }

    }

}