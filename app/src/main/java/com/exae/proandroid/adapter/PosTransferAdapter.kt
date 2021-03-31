package com.exae.proandroid.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exae.proandroid.R
import com.exae.proandroid.bean.TransferListModel
import javax.inject.Inject

/**
 *
 */

class PosTransferAdapter @Inject constructor() :
    BaseQuickAdapter<TransferListModel, BaseViewHolder>(R.layout.view_transfer_item) {

    private var assignRoleId = -1
    private var mPosition = 0

    override fun convert(helper: BaseViewHolder, item: TransferListModel) {
        helper.setText(R.id.mName, item.roleName)
            .setText(R.id.mRole, item.dealerName)
        val position = helper.layoutPosition
        helper.getView<ImageView>(R.id.mChoose).isSelected = mPosition == position
    }

    fun setAssignRoleId(roleId: Int, position: Int) {
        this.assignRoleId = roleId
        this.mPosition = position
    }

}