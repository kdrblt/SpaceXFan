package com.kadirbulut.spacexfan.ext

import android.content.SharedPreferences

inline fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {

    if (!this.contains(key)) {
        return defaultValue
    }

    return when (defaultValue) {
        is String -> getString(key, defaultValue) as T
        is Int -> getInt(key, defaultValue) as T
        is Boolean -> getBoolean(key, defaultValue) as T
        is Float -> getFloat(key, defaultValue) as T
        is Long -> getLong(key, defaultValue) as T
        else -> return defaultValue
    }
}

inline operator fun <reified T> SharedPreferences.set(key: String, value: T) {
    edit().apply {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Long -> putLong(key, value)
            else -> throw UnsupportedOperationException("Type ${T::class} is not supported yet")
        }
    }.apply()
}
