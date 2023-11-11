package com.example.datapersistence_teacherversion_01

import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

const val INITIAL_COUNTER_VALUE = 0
const val INITIAL_SWITCH_VALUE :Boolean = false

class CounterViewModel: ViewModel() {

    private var counter = INITIAL_COUNTER_VALUE

    // ========================================================
    private var seekbarnum = INITIAL_COUNTER_VALUE
    private var switchBool = INITIAL_SWITCH_VALUE

    // same green
    private var seekbarG = INITIAL_COUNTER_VALUE
    private var switchG = INITIAL_SWITCH_VALUE

    // same blue ?????
    private var seekbarB = INITIAL_COUNTER_VALUE
    private var switchB = INITIAL_SWITCH_VALUE

    private val prefs = MyAppRepository.getRepository()

    //---------------------------- saver -----------------------------------
    private fun saveCounter() {
        viewModelScope.launch {
            prefs.saveCounter(counter)
            Log.d(LOG_TAG, "Saving the counter $counter")
        }
    }

    // same saving counter
    private fun saveSeekBar() {
        viewModelScope.launch {
            prefs.saveSeekbarRepo(seekbarnum)
            Log.d(LOG_TAG, "Saving the seekbar $seekbarnum")
        }
    }
    private fun saveSwitch() {
        viewModelScope.launch {
            prefs.saveSwitchRepo(switchBool)
            Log.d(LOG_TAG, "Saving the swicher bool $switchBool")
        }
    }

    // same GREEN
    private fun saveSeekBarG() {
        viewModelScope.launch {
            prefs.saveSeekbarRepoG(seekbarG)
            Log.d(LOG_TAG, "** myCounterView Saving the seekbar green $seekbarG")
        }
    }
    private fun saveSwitchG() {
        viewModelScope.launch {
            prefs.saveSwitchRepoG(switchG)
            Log.d(LOG_TAG, "Saving the swicher green bool $switchG")
        }
    }

    // Same BLUE ??????
    private fun saveSeekBarB() {
        viewModelScope.launch {
            prefs.saveSeekbarRepoB(seekbarB)
            Log.d(LOG_TAG, "** myCounterView Saving the seekbar BLUE $seekbarB")
        }
    }
    private fun saveSwitchB() {
        viewModelScope.launch {
            prefs.saveSwitchRepoB(switchB)
            Log.d(LOG_TAG, "Saving the swicher BLUE bool $switchB")
        }
    }


   // --------------------------------loader----------------------------------------
    fun loadCounter() {
        GlobalScope.launch {
            prefs.counter.collectLatest {
                counter = it
                Log.d(LOG_TAG, "Loaded the counter $counter")
            }
        }
        sleep(1000)
    }
    // same loading function
    fun loadSeekbarCounter() {
        GlobalScope.launch {
            prefs.seekbarCounter.collectLatest {
                seekbarnum = it
                Log.d(LOG_TAG, "Loaded the seekbar valur from dataStore $seekbarnum")
            }
        }
        sleep(1000)
    }

    fun loadSwitcher() {
        GlobalScope.launch {
            prefs.switchRecord.collectLatest {
                switchBool = it
                Log.d(LOG_TAG, "Loaded the switch value from dataStore $switchBool")
            }
        }
        sleep(1000)
    }

    //Same green
    fun loadSeekbarCounterG() {
        GlobalScope.launch {
            prefs.seekbarCounterG.collectLatest {
                seekbarG = it
                Log.d(LOG_TAG, " %%%green Loaded the seekbar valur from dataStore $seekbarnum")
            }
        }

        sleep(1000)
    }

    fun loadSwitcherG() {
        GlobalScope.launch {
            prefs.switchRecordG.collectLatest {
                switchG = it
                Log.d(LOG_TAG, "%%%%444grren Loaded the switch value from dataStore $switchBool")
            }
        }
        sleep(1000)
    }

    // same blue ???????
    fun loadSeekbarCounterB() {
        GlobalScope.launch {
            prefs.seekbarCounterB.collectLatest {
                seekbarB = it
                Log.d(LOG_TAG, " %%% BLUE Loaded the seekbar valur from dataStore $seekbarB")
            }
        }

        sleep(1000)
    }

    fun loadSwitcherB() {
        GlobalScope.launch {
            prefs.switchRecordB.collectLatest {
                switchB = it
                Log.d(LOG_TAG, "%%%% BLUE Loaded the switch value from dataStore $switchB")
            }
        }
        sleep(1000)
    }



//----------------------------getter and setter -----------------------------------
    fun getCounter(): Int {
        return counter
    }
    //***
    fun getSeekbarCounter(): Int {
        return seekbarnum
    }
    fun getSwitch(): Boolean {
        return switchBool
    }

    // same green
    //***
    fun getSeekbarCounterG(): Int {
        return seekbarG
    }
    fun getSwitchG(): Boolean {
        return switchG
    }
    // Same BLUE  ????
    fun getSeekbarCounterB(): Int {
        return seekbarB
    }
    fun getSwitchB(): Boolean {
        return switchB
    }



    fun setCounter(newCounterValue: Int) {
        counter = newCounterValue
        saveCounter()
    }
    //*****
    fun setSeekbarNumber(newCounterValue: Int) {
        seekbarnum = newCounterValue
        saveSeekBar()
        Log.d(LOG_TAG, " ModelView setSeekbarNumber function $seekbarnum")
    }
    fun setSwitch(newCounterValue: Boolean) {
        switchBool = newCounterValue
        saveSwitch()
        Log.d(LOG_TAG, " ModelView setSwitch function $switchBool")
    }


    // same green
    fun setSeekbarNumberG(newCounterValue: Int) {
        seekbarG = newCounterValue
        saveSeekBarG()
        Log.d(LOG_TAG, " ModelView setSeekbarNumber function $seekbarG")
    }
    fun setSwitchG(newCounterValue: Boolean) {
        switchG = newCounterValue
        saveSwitchG()
        Log.d(LOG_TAG, " ModelView setSwitch function $switchG")
    }


    // Same BLUE ??????
    fun setSeekbarNumberB(newCounterValue: Int) {
        seekbarB = newCounterValue
        saveSeekBarB()
        Log.d(LOG_TAG, " ---ModelView setSeekbarNumber function $seekbarB")
    }
    fun setSwitchB(newCounterValue: Boolean) {
        switchB = newCounterValue
        saveSwitchB()
        Log.d(LOG_TAG, " ---ModelView setSwitch function $seekbarB")
    }


}