package com.hg_studio.oneline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.hg_studio.oneline.databinding.ActivityListBinding

class ListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var rvAdapter: ListOnelineRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.listBacktomainIb.setOnClickListener {
            val intent = Intent(this@ListActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.lefttonone, R.anim.none)

            finish()
        }
    }

    override fun onStart() {
        super.onStart()

        val token = getWriterIdx()

        if(token != 0){
            getOnelines(token)
        }
    }

    private fun initRecyclerView() {
        val lm = LinearLayoutManager(this) //영상에선 this 대신 뤼콰이얼액티비티임
        binding.listRecyclerview.layoutManager = lm

        rvAdapter = ListOnelineRvAdapter(this) //영상에선 뤼콰이얼액티비티임

        //binding.listRecyclerview.adapter = OnelineRvAdapter(this,data, onClickDeleteIcon = {deleteTask(it)})
        //oneline안에 있는 trash 버튼을 눌렀을 때 삭제하게 되고 싶음
        //trash = this.findViewById(R.id.item_oneline_trash_ib)

        rvAdapter.setItemClickListener(object: ListOnelineRvAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int, oneline: Oneline) {
                //removeOneline(oneline.idx, position)
            }
        })

        binding.listRecyclerview.adapter = rvAdapter
    }

//    fun deleteTask(oneline: Oneline) {
//        data.remove(oneline)
//        binding.listRecyclerview.adapter?.notifyDataSetChanged()
//    }


    private fun getOnelines(writer: Int){
        val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
        val list = ArrayList(db.onelineDao().getOnelinesByWriter(writer))

        for(item in list){
            Log.d("user-db", "idx: ${item.idx}, oneline: ${item.oneline}, date: ${item.datetime}")
        }

        rvAdapter.addItems(list)
    }

    private fun getWriterIdx() :Int {
        val spf: SharedPreferences = getSharedPreferences("oneline", AppCompatActivity.MODE_PRIVATE)

        return spf.getInt("token", 0)
    }

    //화면누르면 삭제하는게 아니라면 두번째 파라미터 지워주면 되고 {}안에 세번째 줄도 지워주면됨
//    private fun removeOneline(idx: Int, position: Int){
//        val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
//        db.onelineDao().removeOneline(idx)
//
//        rvAdapter.removeItem(position)
//    }
}