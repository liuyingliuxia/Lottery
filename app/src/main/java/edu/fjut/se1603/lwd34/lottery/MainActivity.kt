package edu.fjut.se1603.lwd34.lottery

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.extension.deleteAll
import org.litepal.extension.findAll

class MainActivity : AppCompatActivity() {
    private lateinit var nameAdapter: NameAdapter
    private lateinit var mManager: LinearLayoutManager
    lateinit var result: String
    var mList: ArrayList<InputName> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        if (mList.isEmpty()) {
            for (i in 0..1) { // 默认两行空数据
                val inputName = InputName()
                inputName.name = ""
                mList.add(inputName)
            }
        }
        nameAdapter = NameAdapter(this, mList)
        mManager = LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        rvMain.layoutManager = mManager
        rvMain.adapter = nameAdapter

        tvBegin.setOnClickListener {
            hideKeyboard(it, this)
            mList = nameAdapter.getInput()
          //  nameAdapter.notifyDataSetChanged()

            Log.e("------------------", mList.toString())

            val random = (0 until mList.size).random() // 产生随机结果
            result = mList[random].name
            val pop = PopWndResult.create(result, this, object : PopWndResult.CallBack {
                override fun onClickItem(type: Int) {
                }
            })
            pop.open()
        }

    }

    //强制隐藏软键盘
    private fun hideKeyboard(view: View, myActivity: Activity) {
        view.isFocusable = false
        view.isFocusableInTouchMode = false
        val inputMethodManager =
            myActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }


}
