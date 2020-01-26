package edu.fjut.se1603.lwd34.lottery

import android.annotation.SuppressLint
import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

/**
 * 底部弹出PopWnd
 */
open class PopWndCenterMenu(contentView: View?) : AlphaTransformPopWindow(contentView!!, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true) {
    /**
     * 默认在Activit中间弹出
     */
    @SuppressLint("RtlHardcoded")
    fun show() {
        val activity = contentView.context as Activity
        super.showAtLocation(activity.window.decorView)
        showAtLocation(activity.window.decorView,  Gravity.CENTER, 0, 0)
    }
/*
    init {
       animationStyle = R.style.PushDownAnimation
    }*/
}