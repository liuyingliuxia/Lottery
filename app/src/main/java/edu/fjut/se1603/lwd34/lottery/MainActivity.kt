package edu.fjut.se1603.lwd34.lottery

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.*
import java.util.Collections.swap

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
        for (i in 0..1) {
            val inputName = InputName()
            inputName.id = i
            inputName.name = ""
            mList.add(inputName)
        }
        nameAdapter = NameAdapter(this, mList)
        mManager = LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        rvMain.layoutManager = mManager
        rvMain.adapter = nameAdapter

        tvBegin.setOnClickListener {
            hideKeyforard(it , this)
            val random = (0 until mList.size).random()
            result = mList[random].name
            Log.e("------------------" , mList.toString())
            val pop = PopWndResult.create(result ,this, object : PopWndResult.CallBack {
                override fun onClickItem(type: Int) {
                }
            })
            pop.open()
        }

    }

    //强制隐藏软键盘
    fun hideKeyforard(view: View, myActivity: Activity) {
        view.isFocusable = false
        view.isFocusableInTouchMode = false
        val inputMethodManager = myActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun talk(context: Context, input: String) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show()
    }

}
