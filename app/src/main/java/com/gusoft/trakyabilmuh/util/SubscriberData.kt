package com.gusoft.trakyabilmuh.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList


fun saveSubscribeList(context: Context, list: List<String>, key: String?) {
    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val editor: SharedPreferences.Editor = prefs.edit()
    val gson = Gson()
    val json: String = gson.toJson(list)
    editor.putString(key, json)
    editor.apply()
}

fun getSubscribeList(context: Context, key: String?): List<String> {
    val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    val gson = Gson()
    val json: String? = prefs.getString(key, null)
    val type: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
    if (json.isNullOrBlank()) {
        return emptyList()
    }
    val data: List<String> = gson.fromJson(json, type)
    Log.i("Subscribe List", "getSubscribeList: $data")
    return data
}

