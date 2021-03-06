package edu.fjut.se1603.lwd34.lottery

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_main.view.*

class NameAdapter(var context: Context, var list: ArrayList<InputName>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // var mList = list
    private val ITEM_TYPE = 0
    private val ADD_TYPE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_TYPE) {
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false)
            Holder(itemView)
        } else {
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_add, parent, false)
            Holder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return if (list.size > 0) {
            list.size + 1
        } else 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ADD_TYPE
        } else {
            ITEM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val h = holder.itemView
        if (holder.itemViewType == ITEM_TYPE) {
            h.etItem.hint = "请输入第${position + 1}个选项"

            h.ivSub.setOnClickListener {
                list.removeAt(position)
                notifyDataSetChanged()
            }

            //通过tag判断当前editText是否已经设置监听，有监听的话，移除监听再给editText赋值
            if (h.etItem.tag is TextWatcher) {
                h.etItem.removeTextChangedListener(h.etItem.tag as TextWatcher)
            }
            val textWatcher = object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (p0.isNullOrEmpty()) {
                        h.ivClear.visibility = View.GONE
                    } else {
                        h.ivClear.visibility = View.VISIBLE
                        list[position].name = h.etItem.text.toString()
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        h.ivClear.visibility = View.GONE
                    } else {
                        h.ivClear.visibility = View.VISIBLE
                    }
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (p0.isNullOrEmpty()) {
                        h.ivClear.visibility = View.GONE
                    } else {
                        h.ivClear.visibility = View.VISIBLE

                    }
                }

            }
            h.etItem.addTextChangedListener(textWatcher)
            h.etItem.tag = textWatcher

            h.ivClear.setOnClickListener {
                h.etItem.setText("")
                list[position].name = ""
            }
        } else {
            h.ivAdd.setOnClickListener {
                //应该是 在末尾添加 而不是开头
                val input = InputName()
                input.name = ""
                list.add(input)
                notifyDataSetChanged()
            }
        }

    }

    fun getInput(): ArrayList<InputName> {
        return list
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

