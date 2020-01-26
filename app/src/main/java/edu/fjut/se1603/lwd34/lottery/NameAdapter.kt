package edu.fjut.se1603.lwd34.lottery

import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_add.view.*
import kotlinx.android.synthetic.main.item_main.view.*
import java.util.*
import kotlin.collections.ArrayList

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
            h.etItem.hint = "请输入第${position+1}个选项"

            h.ivSub.setOnClickListener {
                list.removeAt(position)
                notifyDataSetChanged()
            }
            h.etItem.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (p0.isNullOrEmpty()) {
                        h.ivClear.visibility = View.GONE
                    } else {
                        h.ivClear.visibility = View.VISIBLE
                        editInput(h , position)
                      //  bubbleSort(list)
                        //notifyDataSetChanged()
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

            })
            h.ivClear.setOnClickListener {
                h.etItem.setText("")
            }
        } else {
            h.ivAdd.setOnClickListener {
                val input = InputName()
                input.id = list.size
                input.name = ""
                list.add(input)
                notifyDataSetChanged()
            }
        }

    }

    fun bubbleSort(list: ArrayList<InputName>) {
        if (list.size == 0) return
        val maxIndex = list.size - 1
        for (n in 0 until maxIndex) {
            for (i in 0 until maxIndex - n) {
                if (list[i].id > list[i + 1].id) {
                    Collections.swap(list, i, i + 1)
                }
            }
        }
    }

    fun editInput(h: View, position: Int){
        list[position].id = position
        list[position].name = h.etItem.text.toString()
        notifyDataSetChanged()
    }
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}

