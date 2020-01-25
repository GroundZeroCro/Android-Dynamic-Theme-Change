package com.groundzero.dynamicthemechange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var persistenceUtils: PersistenceUtils
    private lateinit var themeType: ThemeType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        persistenceUtils = PersistenceUtils(this).also {
            it.getTheme().apply {
                themeType = this
                setTheme(
                    when (this) {
                        ThemeType.CASUAL -> R.style.AppThemeCasual
                        ThemeType.DARK -> R.style.AppThemeDark
                    }
                )
            }
        }

        setContentView(R.layout.activity_main)
        instantiateChangeThemeButton()
    }

    private fun instantiateChangeThemeButton() =
        change_theme_button.setOnClickListener {
            persistenceUtils.setTheme(
                if (themeType == ThemeType.CASUAL) ThemeType.DARK
                else ThemeType.CASUAL
            ).also {
                finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                startActivity(this@MainActivity.intent)
            }
        }
}