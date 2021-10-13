package com.example.rssfeedpractice

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class rvAdapter (val title: List<RecentQuestions>):RecyclerView.Adapter<rvAdapter.ItemViweHolder>(){
    class ItemViweHolder (itemVew: View):RecyclerView.ViewHolder(itemVew)

var context : Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViweHolder {
      context = parent.getContext()
        return ItemViweHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false))
    }
    override fun onBindViewHolder(holder: ItemViweHolder, position: Int) {
        val qus = title[position]
        holder.itemView.apply{
            textView.text= qus.title
        }
        holder.itemView.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setMessage("${qus.summary}")
                .setNegativeButton("OK", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("${qus.title}")
            alert.show()
        }
    }
    override fun getItemCount() = title.size
}