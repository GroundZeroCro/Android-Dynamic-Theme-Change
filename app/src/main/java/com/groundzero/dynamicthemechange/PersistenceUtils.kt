package com.groundzero.dynamicthemechange

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PersistenceUtils(context: Context) {

    private val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    fun setTheme(themeType: ThemeType) {
        sharedPreferences.edit().putInt(THEME_PERSISTENCE_KEY, themeType.reference).apply()
    }

    fun getTheme(): ThemeType =
        ThemeType.fromInt(sharedPreferences.getInt(THEME_PERSISTENCE_KEY, 0)) ?: ThemeType.CASUAL

    companion object {
        const val THEME_PERSISTENCE_KEY = "theme_persistence"
    }
}