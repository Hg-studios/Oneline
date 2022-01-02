package com.hg_studio.oneline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MainOnelineRvAdapter(private val context: Context): RecyclerView.Adapter<MainOnelineRvAdapter.ViewHolder>() {
    var items = ArrayList<Oneline>()
    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener{
        fun onClick(view: View, position: Int, oneline: Oneline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oneline_without_trash, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position, items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(item: Oneline){
        this.items.add(item)
        notifyDataSetChanged() //데이터가 바뀐 것을 알려줌
    }

    fun setItemClickListener(itemClickListener: MainOnelineRvAdapter.ItemClickListener){
        this.itemClickListener = itemClickListener
    }


    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        val content: TextView = itemView?.findViewById(R.id.item_oneline_ct)!!
        val date: TextView = itemView?.findViewById(R.id.item_oneline_date)!!

        fun bind(item: Oneline){
            content.text = item.oneline
            date.text = item.datetime
        }
    }
}