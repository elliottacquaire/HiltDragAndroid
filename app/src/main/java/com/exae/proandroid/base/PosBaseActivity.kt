package com.exae.proandroid.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationManagerCompat
import com.exae.proandroid.R
import com.exae.proandroid.dialog.MMLoading

open class PosBaseActivity constructor(layout: Int): AppCompatActivity(layout) {

    private var topToolbar : Toolbar? = null
    private var imgHead: ImageView? = null
    private var toolbarTitle: TextView? = null
    private var tvCancel : TextView? = null

    var mLoadingDialog: MMLoading? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        StatusBarHelper.translucent(this)
//        StatusBarHelper.isDayNight(this)
        initToolBar()

        window.navigationBarColor = getColor(R.color.color_black)
    }

    private fun initToolBar() {
        topToolbar = findViewById(R.id.top_toolbar)
        imgHead = findViewById(R.id.img_head)
        toolbarTitle = findViewById(R.id.toolbar_title)
        tvCancel = findViewById(R.id.tv_cancel)


        topToolbar?.fitsSystemWindows = true
        //利用Toolbar代替ActionBar
        setSupportActionBar(topToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        topToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
        imgHead?.visibility = View.GONE
        imgHead?.setOnClickListener {
            rightImageClick()
        }
        tvCancel?.setOnClickListener {
            leftTvClick()
            onBackPressed()
        }
        setSettingImage(false)
        setCancelState(false)
        setBackState(true) //back 箭头 默认显示
    }

    open fun setNavigationIcon(resourceId: Int){
        topToolbar?.setNavigationIcon(resourceId)
    }

    open fun setToolTitle(title: String?){
        title.let {
            toolbarTitle?.text = it
        }
    }

    open fun setBackState(isShow: Boolean){
        if (isShow){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }else{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

    }

    //取消 显示状态
    open fun setCancelState(isShow: Boolean){
        if (isShow){
            tvCancel?.visibility = View.VISIBLE
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }else{
            tvCancel?.visibility = View.GONE
        }
    }

    open fun setSettingImage(isShow: Boolean){
        if (isShow){
            imgHead?.visibility = View.VISIBLE
        }else{
            imgHead?.visibility = View.GONE
        }
    }

    open fun rightImageClick(){

    }

    open fun leftTvClick() {

    }
    fun showLoading(){
        if (mLoadingDialog == null){
            val loadBuilder = MMLoading.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true)
            mLoadingDialog = loadBuilder?.create()
        }
        if (!mLoadingDialog?.isShowing!!){
            mLoadingDialog?.show()
        }

    }

     fun dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog?.isShowing!!) mLoadingDialog?.dismiss()
    }

    private fun isNotificationEnabled() : Boolean{
        var isOpened = false
        isOpened = try {
            NotificationManagerCompat.from(baseContext).areNotificationsEnabled()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        return isOpened
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}