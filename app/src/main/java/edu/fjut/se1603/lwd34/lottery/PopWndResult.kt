package edu.fjut.se1603.lwd34.lottery

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.popwnd_result.view.*

class PopWndResult(result : String , content: Activity, callBack: CallBack) :
    PopWndCenterMenu(LayoutInflater.from(content).inflate(R.layout.popwnd_result, null)) {
    val result = result
    private fun initView( callBack: CallBack) {
        setBackgroundDrawable(ColorDrawable())
        val rootView = contentView
        rootView.tvLotteryName.text = result
        rootView.tvConfirm.setOnClickListener {
            callBack.onClickItem(ITEM_TYPE)
            dismiss()
        }

    }

    interface CallBack {
        fun onClickItem(type: Int)
    }

    fun open() {
        show()
    }

    companion object {
        const val ITEM_TYPE = 0
        fun create(result : String , context: Activity, callback: CallBack): PopWndResult {
            return PopWndResult( result,context, callback)
        }
    }

    init {
        initView(callBack)
    }
}