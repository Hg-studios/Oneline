package com.hg_studio.oneline

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hg_studio.oneline.databinding.ActivityWhoBinding

class WhoActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWhoBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityWhoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.whoNextArrow.setOnClickListener {
            val intent = Intent(this@WhoActivity, WhenActivity::class.java)
            intent.putExtra("oneline", binding.whoTextTv.text.toString())
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }

        binding.whoBackArrow.setOnClickListener {
            val intent = Intent(this@WhoActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
        binding.whoCheck.setOnClickListener {
            val intent = Intent(this@WhoActivity, OnelineActivity::class.java)
            intent.putExtra("oneline", binding.whoTextTv.text.toString())
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

            finish()
        }
    }
}