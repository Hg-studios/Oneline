package com.hg_studio.oneline

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class ListOnelineRvAdapter(private val context: Context): RecyclerView.Adapter<ListOnelineRvAdapter.ViewHolder>(){
    val items = ArrayList<Oneline>()
    private lateinit var itemClickListener: ItemClickListener

    interface ItemClickListener{
        fun onClick(view: View, position: Int, oneline: Oneline)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_oneline, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val listposition = dataSet[position]

        holder.bind(items[position])

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position, items[position])
        }

        holder.trash.setOnClickListener {
            if(items[position].isDelete == "N"){
                deleteOneline(items[position].idx, "Y")
                removeOneline(items[position].idx)
                this.items.removeAt(position)
                notifyDataSetChanged()
            }
        }

//        holder.trash.setOnClickListener {
//            onClickDeleteIcon.invoke(listposition)
//        }
//        holder.trash.setOnClickListener {
//            if(items[position].isDelete == "N"){
//                items[position].isDelete = "Y"
//                val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
//                db.onelineDao().removeOneline(idx)
//
//                rvAdapter.removeItem(position)
//            }
//        }
//        holder.trash.setOnClickListener{
//            if(items[position].isCheck == "N"){
//                val db: OnelineDB = Room.databaseBuilder(ListActivity(), OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
//                db.onelineDao().removeOneline(position)
//            }
//        }

        //??????????????? ??????????????? ???????????? ????????? ???????????? ???????????? ?????? {}?????? ????????? ?????? ???????????????
//        private fun removeOneline(idx: Int, position: Int){
//            val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
//            db.onelineDao().removeOneline(idx)
//
//            rvAdapter.removeItem(position)
//        }
    }
    private fun deleteOneline(idx: Int, deleteOneline: String){
        val db: OnelineDB = Room.databaseBuilder(context, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
        db.onelineDao().deleteOneline(idx,deleteOneline)
    }

    private fun removeOneline(idx: Int){
        val db: OnelineDB = Room.databaseBuilder(context, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
        db.onelineDao().removeOneline(idx)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: ArrayList<Oneline>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged() //???????????? ?????? ?????? ?????????
    }

//    fun removeItem(position: Int){
//        this.items.removeAt(position)
//        notifyDataSetChanged()
//    }

    fun setItemClickListener(itemClickListener: ItemClickListener){
        this.itemClickListener = itemClickListener
    }

    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        val content: TextView = itemView?.findViewById(R.id.item_oneline_ct)!!
        val date: TextView = itemView?.findViewById(R.id.item_oneline_date)!!
        val trash: ImageView = itemView?.findViewById(R.id.item_oneline_trash_ib)!!

        fun bind(item: Oneline){
            content.text = item.oneline
            date.text = item.datetime

        }
    }
}