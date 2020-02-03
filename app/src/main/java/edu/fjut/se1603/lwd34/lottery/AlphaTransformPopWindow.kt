package edu.fjut.se1603.lwd34.lottery

import android.animation.ObjectAnimator
import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow

/**
 * PopupWindow 动画背景透明度
 */
abstract class AlphaTransformPopWindow(contentView: View, width: Int, height: Int, focusable: Boolean) : PopupWindow(contentView, width, height, focusable) {
    private val valueAnimator = ObjectAnimator.ofFloat(1f, 0f).setDuration(500)
    /**
     * 最大透明度
     */
    private val MAX_ALPHA_VALUE = 0.5f
    /**
     * 是否背景显示透明
     */
    var isBackgroundTrans = true

    fun showAtLocation(parent: View) {
        showAtLocation(parent, Gravity.CENTER, 0, 0)
    }

    override fun showAtLocation(parent: View, gravity: Int, x: Int, y: Int) {
        super.showAtLocation(parent, gravity, x, y)
        showTransBackground(true)
    }

    /**
     * 慎用showAsDropDown
     *
     * @param anchor
     */
    override fun showAsDropDown(anchor: View) {
        super.showAsDropDown(anchor)
        showTransBackground(true)
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int) {
        super.showAsDropDown(anchor, xoff, yoff)
        showTransBackground(true)
    }

    private fun showTransBackground(isTrans: Boolean) {
        if (!isBackgroundTrans) {
            return
        }
        val window = (contentView.context as Activity).window ?: return
        // 加上FLAG_DIM_BEHIND标记，解决部分华为手机背景不变暗的BUG，退出时必须clear,否则Activity退出动画会有黑屏一下的情况
        if (isTrans) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        }
        valueAnimator.removeAllListeners()
        valueAnimator.removeAllUpdateListeners()
        valueAnimator.addUpdateListener { animation ->
            val fraction = animation.animatedValue as Float
            val lp = window.attributes
            if (isTrans) {
                lp.alpha = MAX_ALPHA_VALUE + (1 - MAX_ALPHA_VALUE) * fraction
            } else {
                lp.alpha = MAX_ALPHA_VALUE + (1 - fraction) * (1 - MAX_ALPHA_VALUE)
            }
            // 退出动画后
            if (!isTrans && lp.alpha == 1f) { //  加上FLAG_DIM_BEHIND标记，解决部分华为手机背景不变暗的BUG，退出时必须clear,否则Activity退出动画会有黑屏一下的情况
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
            window.attributes = lp
        }
        valueAnimator.start()
    }

    init {
        // 解决部分手机或高版本手机背景不能变暗BUG, （备注：主题中的windowIsTranslucent true也会影响变暗过程）
        val window = (contentView.context as Activity).window
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        isFocusable = true
        isTouchable = true
        isOutsideTouchable = true
        update()
        setBackgroundDrawable(BitmapDrawable())
        contentView.isFocusableInTouchMode = true
        setOnDismissListener { showTransBackground(false) }
    }
}