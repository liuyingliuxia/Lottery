package edu.fjut.se1603.lwd34.lottery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main.*

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
        for (i in 0..3) {
            mList.add(InputName(i, ""))
        }
        nameAdapter = NameAdapter(this, mList)
        mManager = LinearLayoutManager(this)
        mManager.orientation = LinearLayoutManager.VERTICAL
        rvMain.layoutManager = mManager
        rvMain.adapter = nameAdapter


        tvBegin.setOnClickListener {

            nameAdapter.notifyDataSetChanged()
            val random = (0 until mList.size).random()
            result = mList[random].name
          //  talk(this , "-----$result")
            Log.e("------------------" , mList.toString())
            val pop = PopWndResult.create(result ,this, object : PopWndResult.CallBack {
                override fun onClickItem(type: Int) {
                }
            })
            pop.open()
        }

    }
    fun talk(context: Context, input: String) {
        Toast.makeText(context, input, Toast.LENGTH_SHORT).show()
    }

}
