package com.example.datapersistence_teacherversion_01

import android.content.Context
import android.util.Log
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MyAppRepository private constructor(private val dataStore: DataStore<Preferences>) {

    private val counterKey = intPreferencesKey("counter")
    // same red
    private val seekbarKey = intPreferencesKey("seekbar")
    private val switchKey = booleanPreferencesKey("switch")

    // same green
    private val seekbarKeyG = intPreferencesKey("seekbarGreen")
    private val switchKeyG = booleanPreferencesKey("switchGreen")

    // same blue
    private val seekbarKeyB = intPreferencesKey("seekbarBlue")
    private val switchKeyB = booleanPreferencesKey("switchBlue")



    val counter: Flow<Int> = this.dataStore.data.map { prefs ->
        // Saving data async
        prefs[counterKey] ?: INITIAL_COUNTER_VALUE
    } // increasing button

    //same red
    val seekbarCounter: Flow<Int> = this.dataStore.data.map { prefs ->
        // Saving data async *************************** big mistakes before here
        prefs[seekbarKey] ?: INITIAL_COUNTER_VALUE
    }
    //****** async load the data from dataStore
    val switchRecord: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[switchKey] ?: INITIAL_SWITCH_VALUE
    }


    //same green
    val seekbarCounterG: Flow<Int> = this.dataStore.data.map { prefs ->
        // Saving data async *************************** big mistakes before here
        prefs[seekbarKeyG] ?: INITIAL_COUNTER_VALUE
    }
    //****** async load the data from dataStore
    val switchRecordG: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[switchKeyG] ?: INITIAL_SWITCH_VALUE
    }

    //same Blue  ??
    val seekbarCounterB: Flow<Int> = this.dataStore.data.map { prefs ->
        // Saving data async *************************** big mistakes before here
        prefs[seekbarKeyB] ?: INITIAL_COUNTER_VALUE
    }
    //****** async load the data from dataStore
    val switchRecordB: Flow<Boolean> = this.dataStore.data.map { prefs ->
        prefs[switchKeyB] ?: INITIAL_SWITCH_VALUE
    }




    // CounterModelView will call this function
    suspend fun saveCounter(value: Int) {
        saveIntValue(counterKey, value)
    }

    // same red
    // CounterModelView will call this function
    suspend fun saveSeekbarRepo(value: Int) {
        saveSeekbarValue(seekbarKey, value)
    }
// ************* Saving key-value pair from Flow ?, public function
    suspend fun saveSwitchRepo(value: Boolean) {
        saveSwitchValue(switchKey, value)
    }


    // same green
    // CounterModelView will call this function
    suspend fun saveSeekbarRepoG(value: Int) {
        saveSeekbarValueG(seekbarKeyG, value)
    }
    // ************* Saving key-value pair from Flow ?, public function
    suspend fun saveSwitchRepoG(value: Boolean) {
        saveSwitchValueG(switchKeyG, value)
    }

    // ??? same blue
    suspend fun saveSeekbarRepoB(value: Int) {
        saveSeekbarValueB(seekbarKeyB, value)
    }
    // ************* Saving key-value pair from Flow ?, public function
    suspend fun saveSwitchRepoB(value: Boolean) {
        saveSwitchValueB(switchKeyB, value)
    }



    private suspend fun saveIntValue(key: Preferences.Key<Int>, value: Int) {

        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // same
    private suspend fun saveSeekbarValue(key: Preferences.Key<Int>, value: Int) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    //******** Saving the switch value to dataStore, private function
    private suspend fun saveSwitchValue(key: Preferences.Key<Boolean>, value: Boolean) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // same green
    // same
    private suspend fun saveSeekbarValueG(key: Preferences.Key<Int>, value: Int) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    //******** Saving the switch value to dataStore, private function
    private suspend fun saveSwitchValueG(key: Preferences.Key<Boolean>, value: Boolean) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    // same blue ??
    private suspend fun saveSeekbarValueB(key: Preferences.Key<Int>, value: Int) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    //******** Saving the switch value to dataStore, private function
    private suspend fun saveSwitchValueB(key: Preferences.Key<Boolean>, value: Boolean) {
        // dataStore Kotlin way to store number in dictionary and persistent
        this.dataStore.edit { preferences ->
            preferences[key] = value
        }
    }






    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "MyDataStoreFile"
        private var singleInstanceOfMyAppRepository: MyAppRepository? = null
        fun initialize(context: Context) {
            Log.d(LOG_TAG, "initializing MyAppRepository")
            if (singleInstanceOfMyAppRepository == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                singleInstanceOfMyAppRepository = MyAppRepository(dataStore)
            }
        }
        fun getRepository(): MyAppRepository {
            Log.d(LOG_TAG, "getting MyAppRepository")
            return singleInstanceOfMyAppRepository ?: throw IllegalStateException("singleInstanceOfMyAppRepository is not initialized yet")
        }
    }
}