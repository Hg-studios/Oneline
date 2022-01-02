package com.hg_studio.oneline

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.hg_studio.oneline.databinding.ActivityInfoBinding

class InfoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUser()

        binding.infoBacktomainIv.setOnClickListener {
            val intent = Intent(this@InfoActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.none, R.anim.nonetoright)

            finish()
        }

        binding.infoLogoutBox.setOnClickListener {
            logout()

            finish()
        }
    }

    private fun logout(){
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    private fun setUser(){
        val spf: SharedPreferences = getSharedPreferences("oneline", MODE_PRIVATE)
        val token = spf.getInt("token",0)
        //val spf: SharedPreferences = requireActivity().getSharedPreferences("", MODE_PRIVATE)
        //val token = spf.getInt("token",0)

        //if(token != 0){
        //    getUser(token)
        //}
        val db: UserDB = Room.databaseBuilder(this, UserDB::class.java, "user-db").allowMainThreadQueries().build()

        binding.infoUsernameTv.text = db.userDao().getName(token)
    }
}