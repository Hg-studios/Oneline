package com.hg_studio.oneline

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hg_studio.oneline.databinding.ActivityWhereBinding

class WhereActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWhereBinding
    private lateinit var oneline: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhereBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //oneline 내용 가져오기
        intent?. let{
            it.getStringExtra("oneline")?.let{ content->
                oneline = content
            }
        }

        binding.whereNextArrow.setOnClickListener {
            val intent = Intent(this@WhereActivity, WhatActivity::class.java)
            //who에서 썼던 내용에다가 when내용을 붙임
            oneline += " "
            oneline += binding.whereTextTv.text.toString()
            //when까지 합쳐진 내용을 다시 oneline Extra에다가 저장함
            intent.putExtra("oneline", oneline)

            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
        binding.whereBackArrow.setOnClickListener {
            val intent = Intent(this@WhereActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
        binding.whereCheck.setOnClickListener {
            val intent = Intent(this@WhereActivity, OnelineActivity::class.java)
            //who에서 썼던 내용에다가 when내용을 붙임
            oneline += " "
            oneline += binding.whereTextTv.text.toString()
            //when까지 합쳐진 내용을 다시 oneline Extra에다가 저장함
            intent.putExtra("oneline", oneline)

            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
    }
}