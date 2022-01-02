package com.hg_studio.oneline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.hg_studio.oneline.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: MainOnelineRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.mainNewmemoIv.setOnClickListener{
            val intent = Intent(this@MainActivity, WhoActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            startActivity(intent)
        }
        binding.mainMenuBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, InfoActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            startActivity(intent)
        }
        binding.mainListTv.setOnClickListener {
            val intent = Intent(this@MainActivity, ListActivity::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            startActivity(intent)
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
        val lm = LinearLayoutManager(this)
        binding.mainRecyclerview.layoutManager = lm

        rvAdapter = MainOnelineRvAdapter(this)

//        rvAdapter.setItemClickListener(object: MainOnelineRvAdapter.ItemClickListener {
//            override fun onClick(view: View, position: Int, oneline: Oneline) {
//                //removeOneline(oneline.idx, position)
//            }
//        })

        binding.mainRecyclerview.adapter = rvAdapter
    }

    private fun getOnelines(writer: Int){
        val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
        val list = ArrayList(db.onelineDao().getOnelinesByWriter(writer))

//        if (list.size > 1){
//            rvAdapter.addItems(list[0])
//        }
//        if (list.size >2){
//            rvAdapter.addItems(list[1])
//        }
//        if (list.size >3){
//            rvAdapter.addItems(list[2])
//        }

        if(list.size < 3){
            for(i in 0 until list.size){ // 데이터의 개수가 0, 1, 2 개에 따라
                rvAdapter.addItems(list.get(i))
            }
        }else{
            for(i in 0 until 3){ // 데이터가 개수가 3개 이상일 때 0, 1, 2 아이템만 가져오기
                rvAdapter.addItems(list.get(i))
            }
        }

        for(item in list){
            Log.d("user-db", "idx: ${item.idx}, oneline: ${item.oneline}, date: ${item.datetime}")
        }
    }

    private fun getWriterIdx() :Int {
        val spf: SharedPreferences = getSharedPreferences("oneline", AppCompatActivity.MODE_PRIVATE)

        return spf.getInt("token", 0)
    }

}