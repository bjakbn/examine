
package com.examine.app.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.examine.app.data.Event

class EventAdapter(private val list: List<Event>) :
    RecyclerView.Adapter<EventAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val e = list[position]

        val t1 = holder.itemView.findViewById<TextView>(android.R.id.text1)
        val t2 = holder.itemView.findViewById<TextView>(android.R.id.text2)

        t1.text = "${e.type}  ${e.path}"
        t2.text = e.packageName

        holder.itemView.setOnClickListener {
            // 未来可扩展：打开文件/应用
        }

        holder.itemView.setOnLongClickListener {
            val cm = holder.itemView.context
                .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            cm.setPrimaryClip(ClipData.newPlainText("path", e.path))
            true
        }
    }

    override fun getItemCount() = list.size
    }
