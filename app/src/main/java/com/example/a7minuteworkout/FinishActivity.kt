package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)


        setSupportActionBar(toolBar_finish_activity)
        val actionBar = supportActionBar
        if (actionBar != null){

            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        toolBar_finish_activity.setNavigationOnClickListener{
            onBackPressed()
        }
        btnFinish.setOnClickListener{
            finish()
        }
    }
}