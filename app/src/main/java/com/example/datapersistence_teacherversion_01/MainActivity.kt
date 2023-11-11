package com.example.datapersistence_teacherversion_01

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

const val TAG = "SEEKBAR"
const val LOG_TAG = "DataStoreExample"
const val COUNTER_KEY = "CounterKey"
const val SEEKBAR_KEY = "SeekbarKey"
const val SWITCH_KEY = "SwitchKey"

class MainActivity : AppCompatActivity() {

    private lateinit var counterText: TextView
    private lateinit var increaseButton: Button

    //****
    private lateinit var seekBarOne: SeekBar
    private lateinit var seekBarText: TextView
    private lateinit var switchButton: Switch
    private lateinit var rectView: View

    //**** green
    private lateinit var seekBarGreen: SeekBar
    private lateinit var textGreen: TextView
    private lateinit var turnGreen: Switch

    //**** blue
    private lateinit var seekBarBlue: SeekBar
    private lateinit var textBlue: TextView
    private lateinit var turnBlue: Switch


    private val counterViewModel: CounterViewModel by lazy {
        MyAppRepository.initialize(this)
        ViewModelProvider(this)[CounterViewModel::class.java]
    }

    private fun updateRectColor() {
        var red = 0
        var green = 0
        var blue = 0

        if (counterViewModel.getSwitch()) {
            red = (counterViewModel.getSeekbarCounter() / 10.0 * 255).toInt()
        }

        if (counterViewModel.getSwitchG()) {
            green = (counterViewModel.getSeekbarCounterG() / 10.0 * 255).toInt()
        }


        if (counterViewModel.getSwitchB()) {
            blue = (counterViewModel.getSeekbarCounterB() / 10.0 * 255).toInt()
        }



        rectView.setBackgroundColor(Color.rgb(red, green, blue))
        Log.i(TAG, "**** updateRectColor() inside value is red: $red")
        Log.i(TAG, "**** updateRectColor() inside value is green: $green")
        Log.i(TAG, "**** updateRectColor() inside value is green: $blue")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterText = findViewById(R.id.counter_text)
        increaseButton = findViewById(R.id.increase_button)

        //**** red
        seekBarOne = findViewById(R.id.seekbar_one)
        seekBarText = findViewById(R.id.text_one)
        switchButton = findViewById(R.id.switch_one)

        //**** green part link with view
        seekBarGreen = findViewById(R.id.seekbar_two)
        textGreen = findViewById(R.id.text_two)
        turnGreen = findViewById(R.id.switch_two)

        //**** blue part link with view   ???
        seekBarBlue = findViewById(R.id.seekbar_three)
        textBlue = findViewById(R.id.text_three)
        turnBlue = findViewById(R.id.switch_three)


        rectView = findViewById(R.id.rect_view)
        // Load switch status from DataStore


        //================================== Model Part Start ===================================
        counterViewModel.loadSwitcher()
        var checkerone = counterViewModel.getSwitch()
        Log.i(TAG, "**** getSwitch() function return checkerOne value is: $checkerone")

        // Set seekBar and seekBarText based on the loaded switch state
        seekBarOne.isEnabled = checkerone
        seekBarText.isEnabled = checkerone
        // Set the initial state of the switch button
        switchButton.isChecked = checkerone

        // Update the color based on the initial switch state
        updateRectColor()

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            // Update the switch status in ViewModel
            counterViewModel.setSwitch(isChecked)
            Log.i(TAG, "**** Inside Switch isChecked value is: $isChecked")

            // Now you can use the switch status as needed
            if (counterViewModel.getSwitch()) {
                // Switch is ON
                Log.i(TAG, "Switch red is ON")
                val counterValue = counterViewModel.getCounter()

                val newCounterValue = counterValue + 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()

            } else {
                // Switch is OFF
                Log.i(TAG, "Switch red is OFF")
                val counterValue = counterViewModel.getCounter()
                val newCounterValue = counterValue - 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()
            }

            // Set seekBar and seekBarText based on the final switch state
            seekBarOne.isEnabled = isChecked
            seekBarText.isEnabled = isChecked

            // Update the color when the switch changes
            updateRectColor()
        }


        //---------------------------------green ---------------------------------
        counterViewModel.loadSwitcherG()
        var checkerG = counterViewModel.getSwitchG()
        Log.i(TAG, "**** getSwitch() function return checkerOne value is: $checkerG")

        // Set seekBar and seekBarText based on the loaded switch state
        seekBarGreen.isEnabled = checkerG
        textGreen.isEnabled = checkerG
        // Set the initial state of the switch button
        turnGreen.isChecked = checkerG

        // Update the color based on the initial switch state
        updateRectColor()

        turnGreen.setOnCheckedChangeListener { _, isChecked ->
            // Update the switch status in ViewModel

            counterViewModel.setSwitchG(isChecked)
            Log.i(TAG, "**** Inside green Switch isChecked value is: $isChecked")

            // Now you can use the switch status as needed
            if (counterViewModel.getSwitchG()) {
                // Switch is ON
                Log.i(TAG, "Switch green is ON")
                val counterValue = counterViewModel.getCounter()
                val newCounterValue = counterValue + 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()

            } else {
                // Switch is OFF
                Log.i(TAG, "Switch green is OFF")
                val counterValue = counterViewModel.getCounter()
                val newCounterValue = counterValue - 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()
            }

            // Set seekBar and seekBarText based on the final switch state
            seekBarGreen.isEnabled = isChecked
            textGreen.isEnabled = isChecked

            // Update the color when the switch changes
            updateRectColor()
        }


        seekBarOne.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                Log.d(LOG_TAG, "The setSeekbarNumber value is from file is $progress")

                seekBarText.text = progress.toString()
                counterViewModel.setSeekbarNumber(progress)

                // Update the color when the seekbar changes
                updateRectColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        counterViewModel.loadSeekbarCounter()
        seekBarText.text = counterViewModel.getSeekbarCounter().toString()
        seekBarOne.progress = counterViewModel.getSeekbarCounter()
        updateRectColor()




        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                Log.d(LOG_TAG, "The setSeekbarNumber value is from file is $progress")

                textGreen.text = progress.toString()
                counterViewModel.setSeekbarNumberG(progress)
                Log.d(LOG_TAG, "@@@ setting green is  $progress")

                // Update the color when the seekbar changes
                updateRectColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        counterViewModel.loadSeekbarCounterG()
        textGreen.text = counterViewModel.getSeekbarCounterG().toString()
        seekBarGreen.progress = counterViewModel.getSeekbarCounterG()
        var g = counterViewModel.getSeekbarCounterG()
        Log.d(LOG_TAG, "@@@ getting green is  $g")
        updateRectColor()

        Log.d(LOG_TAG, "@@@ updating color green is  ")

        //*****************


        //---------------------------------blue ?? ---------------------------------
        counterViewModel.loadSwitcherB()
        var checkerB = counterViewModel.getSwitchB()
        Log.i(TAG, "**** getSwitch() function return checkerOne  BLUE value is: $checkerB")

        // Set seekBar and seekBarText based on the loaded switch state
        seekBarBlue.isEnabled = checkerB
        textBlue.isEnabled = checkerB
        // Set the initial state of the switch button
        turnBlue.isChecked = checkerB

        // Update the color based on the initial switch state
        updateRectColor()

        turnBlue.setOnCheckedChangeListener { _, isChecked ->
            // Update the switch status in ViewModel

            counterViewModel.setSwitchB(isChecked)
            Log.i(TAG, "**** Inside blue Switch isChecked B value is: $isChecked")

            // Now you can use the switch status as needed
            if (counterViewModel.getSwitchB()) {
                // Switch is ON
                Log.i(TAG, "Switch blue is ON")
                val counterValue = counterViewModel.getCounter()
                val newCounterValue = counterValue + 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()

            } else {
                // Switch is OFF
                Log.i(TAG, "Switch blue is OFF")
                val counterValue = counterViewModel.getCounter()
                val newCounterValue = counterValue - 1
                counterViewModel.setCounter(newCounterValue)
                counterText.text = newCounterValue.toString()
            }

            // Set seekBar and seekBarText based on the final switch state
            seekBarBlue.isEnabled = isChecked
            textBlue.isEnabled = isChecked

            // Update the color when the switch changes
            updateRectColor()
        }
        //??????????????

        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")

                Log.d(LOG_TAG, "The setSeekbarNumber value is from file is $progress")

                textBlue.text = progress.toString()
                counterViewModel.setSeekbarNumberB(progress)
                Log.d(LOG_TAG, "@@@ setting BLUE is  $progress")

                // Update the color when the seekbar changes
                updateRectColor()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        counterViewModel.loadSeekbarCounterB()
        textBlue.text = counterViewModel.getSeekbarCounterB().toString()
        seekBarBlue.progress = counterViewModel.getSeekbarCounterB()
        var b = counterViewModel.getSeekbarCounterB()
        Log.d(LOG_TAG, "@@@ getting BLUE is  $b")
        updateRectColor()

        Log.d(LOG_TAG, "@@@ updating color BLUE is  ")

        //***********************************************





        // Button works in this way: Adding one
        // *** Here is the front-end view, the button trigger the value changed from 0 to how many times clicking the button
        increaseButton.setOnClickListener {
            val counterValue = counterViewModel.getCounter()
            val newCounterValue = counterValue + 1
            counterViewModel.setCounter(newCounterValue)
            counterText.text = newCounterValue.toString()

            // Update the color when the counter changes
            updateRectColor()
        }

        // Loading the number in DataStore when the app is opened
        counterViewModel.loadCounter()
        counterText.text = counterViewModel.getCounter().toString()
    }


    //===================================== DataStore ========================================
    // Check if the input is a valid decimal number
    private fun validateInput(s: CharSequence?) {
        try {
            val input = s?.toString()?.toFloat()
            if (input == null || input > 1.0) {
                // Invalid input, do nothing
            }
        } catch (e: NumberFormatException) {
            // Handle the case where the input is not a valid number, do nothing
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "The counter and seekbar values are saved")

        // Ensure that the ViewModel has the latest values loaded from DataStore
        counterViewModel.loadCounter()
        counterViewModel.loadSeekbarCounter()
        counterViewModel.loadSwitcher()

        // Save the values to the bundle
        outState.putInt(COUNTER_KEY, counterViewModel.getCounter())
        outState.putInt(SEEKBAR_KEY, counterViewModel.getSeekbarCounter())
        outState.putBoolean(SWITCH_KEY, counterViewModel.getSwitch())
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause called")
    }
}

