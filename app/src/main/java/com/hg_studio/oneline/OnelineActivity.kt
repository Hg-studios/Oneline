package com.hg_studio.oneline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.hg_studio.oneline.databinding.ActivityOnelineBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OnelineActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOnelineBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnelineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //oneline 내용 가져오기
        intent?. let{
            it.getStringExtra("oneline")?.let{ content->
                binding.onelineOnelineCt.text = content
            }
        }

        //date 가져오기
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMdd")
        val formatted = current.format(formatter)
        binding.onelineOnelineDate.setText(formatted.toString())

        binding.onelineBackArrow.setOnClickListener{
            val intent = Intent(this@OnelineActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }

        binding.onelineCheck.setOnClickListener(this)

        binding.onelineCheck.setOnClickListener {
            write()
        }
    }

    override fun onClick(v: View?){
        //super.onClick(v)
        //when(v){
        //    binding.onelineBackArrow -> finish()
        //    binding.onelineCheck -> write()
        //}
    }

    private fun write(){
        val content = binding.onelineOnelineCt.text.toString()

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MMdd")
        val formatted = current.format(formatter)

        val spf : SharedPreferences = getSharedPreferences("oneline", MODE_PRIVATE)
        val token = spf.getInt("token",0)

        if(token != 0){
            val oneline = Oneline(oneline = content, datetime = formatted.toString(), writer = token)

            val db: OnelineDB = Room.databaseBuilder(this, OnelineDB::class.java, "oneline-db").allowMainThreadQueries().build()
            db.onelineDao().writeOneline(oneline)

            //val onelines = db.onelineDao().getOnelines()
            //for(_oneline in onelines){
            //    Log.d("oneline-db", "idx: ${_oneline.idx}, oneline: ${_oneline.oneline}, date: ${_oneline.datetime}, userName: ${_oneline.writer}")
            //}

            val intent = Intent(this@OnelineActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
    }
}