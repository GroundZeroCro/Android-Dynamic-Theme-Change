package com.groundzero.dynamicthemechange

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var persistenceUtils: PersistenceUtils
    private lateinit var themeType: ThemeType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        persistenceUtils = PersistenceUtils(this)
        setPersistenceTheme()
        setContentView(R.layout.activity_main)
        change_theme_button.setOnClickListener(changeThemeButtonListener())
    }

    private fun setPersistenceTheme() {
        setTheme(
            when (persistenceUtils.getTheme().also { themeType = it }) {
                ThemeType.CASUAL -> R.style.AppThemeCasual
                ThemeType.DARK -> R.style.AppThemeDark
            }
        )
    }

    private fun changeThemeButtonListener(): View.OnClickListener =
        View.OnClickListener {
            persistenceUtils.setTheme(
                if (themeType == ThemeType.CASUAL) ThemeType.DARK
                else ThemeType.CASUAL
            ).also { restartActivity() }
        }


    private fun restartActivity() {
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        startActivity(this@MainActivity.intent)
    }
}