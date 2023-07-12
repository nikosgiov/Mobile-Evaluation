package com.nikosgiov.evaluationmobile.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesUtils {
    private const val SHARED_PREFS_NAME = "TokenSharedPrefs"
    private const val TOKEN_KEY = "token"
    private const val TOKEN_TYPE_KEY = "tokenType"

    fun saveTokenInSharedPreferences(context: Context, token: String, tokenType: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences("TokenSharedPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString("token", token)
        editor.putString("tokenType", tokenType)
        editor.apply()
    }

    fun retrieveTokenFromSharedPreferences(context: Context): String {
        val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN_KEY, null) ?: ""
    }

    fun retrieveTokenTypeFromSharedPreferences(context: Context): String {
        val sharedPref: SharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(TOKEN_TYPE_KEY, null) ?: ""
    }
}
