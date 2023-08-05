package com.chivata.app1.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chivata.app1.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val DARK_MODE_VALUE = "dark_mode_value"
        const val BLUETOOTH_VALUE = "bluetooth_value"
        const val VIBRATION_VALUE = "vibration_value"
    }

    private lateinit var binding: ActivitySettingsBinding
    private var firstTime:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.switchDarkMode.isChecked = settingsModel.darkMode
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchVibration.isChecked = settingsModel.vibration
                        binding.rsSettings.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }
                }
            }
        }
        initUI()
    }

    private fun initUI() {
        binding.rsSettings.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch { saveVolume(value.toInt()) }
        }

        binding.switchDarkMode.setOnCheckedChangeListener { _, b ->

            if(b) enableDarkMode() else disableDarkMode()
            CoroutineScope(Dispatchers.IO).launch { saveOptions(DARK_MODE_VALUE, b) }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch { saveOptions(BLUETOOTH_VALUE, b) }
        }

        binding.switchVibration.setOnCheckedChangeListener { _, b ->
            CoroutineScope(Dispatchers.IO).launch { saveOptions(VIBRATION_VALUE, b) }
        }
    }

    private suspend fun saveVolume(value: Int) {
        dataStore.edit { preference -> preference[intPreferencesKey(VOLUME_LVL)] = value }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preference -> preference[booleanPreferencesKey(key)] = value }
    }

    private fun getSettings(): Flow<SettingsModel> {
        return dataStore.data.map { preference ->
            SettingsModel(
                volume = preference[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preference[booleanPreferencesKey(BLUETOOTH_VALUE)] ?: false,
                darkMode = preference[booleanPreferencesKey(DARK_MODE_VALUE)] ?: false,
                vibration = preference[booleanPreferencesKey(VIBRATION_VALUE)] ?: true
            )
        }
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}