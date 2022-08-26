package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_excercise.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_custom_back_confirmation.*
import java.util.*
import kotlin.collections.ArrayList

class ExcerciseActivity : AppCompatActivity(),TextToSpeech.OnInitListener  {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var ExerciseTimer: CountDownTimer? = null
    private var ExerciseProgress = 0
    private var ExerciseTimerDuration:Long = 30

    private var exerciseList:ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1

    private var tts:TextToSpeech? = null

    private var player:MediaPlayer? = null

    private var exerciseAdapter:ExerciseStatusAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise)

        setSupportActionBar(toolbar_activity_excercise)
        val actionbar = supportActionBar
        if (actionbar != null)
        {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_activity_excercise.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        tts = TextToSpeech(this,this)



        exerciseList = Constants.defaultExerciseList()
        setupRestView()
        setupExerciseStatusRecycleView()

    }

    override fun onDestroy() {
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if (ExerciseTimer != null){
            ExerciseTimer!!.cancel()
            ExerciseProgress = 0
        }

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null){
            player!!.stop()
        }

        super.onDestroy()
    }

    private fun setRestProgressBar(){
        progressBar.progress=restProgress
        restTimer = object : CountDownTimer(10000,1000) {
            override fun onTick(p0: Long) {
                restProgress++
                progressBar.progress = 10- restProgress
                tv_Timer.text = (10- restProgress).toString()
            }

            override fun onFinish() {

                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()

            }
        }.start()
    }


    private fun setExerciseProgressBar(){
        progressBarExersice.progress=ExerciseProgress
        ExerciseTimer = object : CountDownTimer(ExerciseTimerDuration * 1000,1000) {
            override fun onTick(p0: Long) {
                ExerciseProgress++
                progressBarExersice.progress = ExerciseTimerDuration.toInt()- ExerciseProgress
                tv_ExerciseTimer.text = (ExerciseTimerDuration.toInt()- ExerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList?.size!! - 1 ){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val intent = Intent(this@ExcerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setupExerciseView(){
        try {
        player = MediaPlayer.create(applicationContext,R.raw.press_start)

        player!!.isLooping = false
        player!!.start()

        } catch (e:Exception){
            e.printStackTrace()
        }


        ll_RestView.visibility = View.GONE
        ll_ExerciseView.visibility = View.VISIBLE


        if (ExerciseTimer != null){
            ExerciseTimer!!.cancel()
            ExerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        
        setExerciseProgressBar()

        ivImage.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tv_ExerciseName.text = exerciseList!![currentExercisePosition].getName()
    }


    private fun setupRestView(){

        ll_RestView.visibility = View.VISIBLE
        ll_ExerciseView.visibility = View.GONE


        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }
        setRestProgressBar()

        tvUpcomingExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS ){
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result== TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts","Language not supported !")
            }
        }else {
            Log.e("tts","Initialization Failed")
        }
    }

    private fun speakOut(text :String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun setupExerciseStatusRecycleView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!! , this )
        rvExerciseStatus.adapter = exerciseAdapter

    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)

        customDialog.setContentView(R.layout.dialog_custom_back_confirmation)
        customDialog.tvYes.setOnClickListener{

            finish()
            customDialog.dismiss()
        }

        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }

        customDialog.show()

    }

}