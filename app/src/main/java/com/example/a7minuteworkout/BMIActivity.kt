package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmiactivity.*
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.pow

class BMIActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    var currentVisibleView:String = METRIC_UNITS_VIEW


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)


        setSupportActionBar(toolbar_BMI_activity)

        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "CALCULATE BMI"

        }

        toolbar_BMI_activity.setNavigationOnClickListener{
            onBackPressed()
        }
        btnCalculateUnits.setOnClickListener{
           if(currentVisibleView.equals(METRIC_UNITS_VIEW)){
               if (validMetricUnits()){
                   val heightValue : Float = etMetricUnitHeight.text.toString().toFloat()/100
                   val weightValue : Float = etMetricUnitWeight.text.toString().toFloat()

                   val bmi = weightValue / (heightValue*heightValue)
                   displayBMIresult(bmi)

               }else{
                   Toast.makeText(this,"Please Enter Valid Values",Toast.LENGTH_SHORT).show()
               }
           }else{
               if (validUsUnits()){

                   val usUnitHeightValueFeet: String = etUsUnitHeightFeet.text.toString()
                   val usUnitHeightValueInch: String = etUsUnitHeightInch.text.toString()
                   val usUnitWeightValue: Float = etUsUnitWeight.text.toString().toFloat()

                   val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                   val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))
                   displayBMIresult(bmi)

               }else{
                   Toast.makeText(this@BMIActivity,"Please Enter Valid Values",Toast.LENGTH_SHORT).show()
               }
           }
        }

        makeVisibleUsUnitsView()
        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }
        }
    }

    private fun makeVisibleUsUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        tilMetricUnitHeight.visibility= View.GONE
        tilMetricUnitWeight.visibility= View.GONE

        etUsUnitWeight.text!!.clear()
        etUsUnitHeightFeet.text!!.clear()
        etUsUnitHeightInch.text!!.clear()

        tilUsUnitWeight.visibility = View.VISIBLE
        llUsUnitsHeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        tilMetricUnitHeight.visibility= View.VISIBLE
        tilMetricUnitWeight.visibility= View.VISIBLE

        etMetricUnitHeight.text!!.clear()
        etMetricUnitWeight.text!!.clear()

        tilUsUnitWeight.visibility = View.GONE
        llUsUnitsHeight.visibility = View.GONE

        llDisplayBMIResult.visibility = View.INVISIBLE
    }

    private fun displayBMIresult(BMI : Float){
        val BMIlable : String
        val BMIDescription : String

        if(BMI.compareTo(15f) <= 0){
            BMIlable = "Very Severely Underweight"
            BMIDescription = " Oops! Eat More "
        }else if(BMI.compareTo(15f) >= 0 && BMI.compareTo(16f) <= 0){
            BMIlable = "Severely Underweight"
            BMIDescription = "Oops! You Really Need Take Care \n Of YourSelf! Eat More !!"
        }else if(BMI.compareTo(16f) >= 0 && BMI.compareTo(18.5f) <= 0){
            BMIlable = "Underweight"
            BMIDescription = "Oops! You Really Need Take Care \n Of YourSelf! Eat More !!"
        }else if(BMI.compareTo(18.5f) >= 0 && BMI.compareTo(25f) <= 0) {
            BMIlable = "Normal"
            BMIDescription = "Congratulations! You Are in Good Shape :)"
        }else if(BMI.compareTo(25f) >= 0 && BMI.compareTo(30f) <= 0) {
            BMIlable = "Overweight"
            BMIDescription = "Oops! You Really Need Take Care \n Of YourSelf! WorkOut More !!"
        }else if(BMI.compareTo(30f) >= 0 && BMI.compareTo(35f) <= 0) {
            BMIlable = "Moderately Obese"
            BMIDescription = "Oops! You Really Need Take Care \n Of YourSelf! WorkOut More !!"
        }else if(BMI.compareTo(35f) >= 0 && BMI.compareTo(40f) <= 0) {
            BMIlable = "Severely Obese"
            BMIDescription = "OMG! You Are in Very Dangerous Condition! Act Now "
        }else {
            BMIlable = "Very Severely Obese"
            BMIDescription = "OMG! You Are in Very Dangerous Condition! Act Now "
        }

        llDisplayBMIResult.visibility=View.VISIBLE

        tvBMIType.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvYourBMI.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE

        val BMIValue = BigDecimal(BMI.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = BMIValue
        tvBMIType.text = BMIlable
        tvBMIDescription.text = BMIDescription

    }


    private fun validMetricUnits():Boolean{
        var isValid = true

        when {
            etMetricUnitWeight.text.toString().isEmpty() -> isValid = false
            etMetricUnitHeight.text.toString().isEmpty() -> isValid = false
        }
        return isValid

    }

    private fun validUsUnits():Boolean{
        var isValid = true

        when {
            etUsUnitHeightFeet.text.toString().isEmpty() -> isValid = false
            etUsUnitHeightInch.text.toString().isEmpty() -> isValid = false
            etMetricUnitWeight.text.toString().isEmpty() -> isValid = false
        }
        return isValid
    }
}