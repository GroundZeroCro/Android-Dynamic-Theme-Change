package com.groundzero.dynamicthemechange

enum class ThemeType(var reference: Int) {
    CASUAL(0),
    DARK(1);

    companion object {
        private val map = values().associateBy(ThemeType::reference)
        fun fromInt(type: Int) = map[type]
    }
}