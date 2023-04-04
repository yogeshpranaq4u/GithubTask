package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.model.Data


class RecyclerAdapter(val list: ArrayList<Data?>?, val context: Context) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView? = null
        var desc: TextView? = null

        init {
            textView = itemView.findViewById(R.id.text)
            desc = itemView.findViewById(R.id.desc)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        println("MainActivity.onCreate 12-> ${list?.size}")
        return list?.size?:return 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView?.text = list?.get(position)?.get(position)?.url
        holder.desc?.text = list?.get(position)?.get(position)?.description

        holder.desc?.setOnClickListener {
            context.startActivity(Intent(context, AddRepo::class.java)
                .putExtra("url",list?.get(position)?.get(position)?.url ))

        }
    }

}