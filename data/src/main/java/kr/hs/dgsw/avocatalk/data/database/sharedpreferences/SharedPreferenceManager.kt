package kr.hs.dgsw.personer.project.lime_android.database.sharedpreferences

import android.content.Context
import android.content.SharedPreferences


object SharedPreferenceManager {

    private const val PREF_ACCOUNT_ID = "pref_user_id"
    private const val PREF_KEYBOARD_HEIGHT = "pref_keyboard_height"

    fun getUserID(context: Context): String {
        return getDefaultSharedPreferences(context).getString(PREF_ACCOUNT_ID, "")!!
    }

    fun insertUserID(context: Context, data: String) {
        getDefaultSharedPreferences(context).edit().putString(PREF_ACCOUNT_ID, data).apply()
    }

    fun deleteUserID(context: Context) {
        getDefaultSharedPreferences(context).edit().remove(PREF_KEYBOARD_HEIGHT).apply()
    }


    fun getKeyboardHeight(context: Context): Int {
        return getDefaultSharedPreferences(context).getInt(PREF_KEYBOARD_HEIGHT, 0)
    }

    fun insertKeyboardHeight(context: Context, data: Int) {
        getDefaultSharedPreferences(context).edit().putInt(PREF_KEYBOARD_HEIGHT, data).apply()
    }

    fun deleteKeyboardHeight(context: Context) {
        getDefaultSharedPreferences(context).edit().remove(PREF_KEYBOARD_HEIGHT).apply()
    }


    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName.toString() + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }
}