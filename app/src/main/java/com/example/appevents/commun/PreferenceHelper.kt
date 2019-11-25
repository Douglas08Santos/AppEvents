package com.example.appevents.commun

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {

    fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager
        .getDefaultSharedPreferences(context)

    fun customPrefs(context: Context, name: String): SharedPreferences = context
        .getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(tokenType: String, value: Any?) {
        when (value) {
            is String? -> edit({ it.putString(tokenType, value) })
            is Int -> edit({ it.putInt(tokenType, value) })
            is Boolean -> edit({ it.putBoolean(tokenType, value) })
            is Float -> edit({ it.putFloat(tokenType, value) })
            is Long -> edit({ it.putLong(tokenType, value) })
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String,
                                                                        defaultValue: T? = null): T?{
        return when (T::class) {
            kotlin.String::class -> getString(key, defaultValue as? String) as T?
            kotlin.Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            kotlin.Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            kotlin.Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            kotlin.Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }
}