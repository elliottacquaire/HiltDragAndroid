package com.exae.proandroid.common

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.exae.proandroid.CusApplication
import dagger.hilt.android.qualifiers.ApplicationContext

object ShareUtil {


    fun removeKey(key : String){
        if (key.isNullOrEmpty())return
        val mSharedPreferences = CusApplication.instance().getSharedPreferences(
            "TRACK_RECORD",
            Context.MODE_PRIVATE)
        mSharedPreferences.edit().remove(key).apply()
    }

    fun getKey(key : String) : String?{
        if (key.isEmpty() || key == "0")return ""
        val mSharedPreferences = CusApplication.instance().getSharedPreferences(
            "TRACK_RECORD",
            Context.MODE_PRIVATE)
        return mSharedPreferences.getString(key,"")
    }

    fun putKey(key: String,token:String){
        val mSharedPreferences = CusApplication.instance().getSharedPreferences("TRACK_RECORD", Context.MODE_PRIVATE)
        mSharedPreferences?.edit()?.putString(key, token)?.apply()
    }

    fun getToken(): String?{
        val mSharedPreferences = CusApplication.instance().getSharedPreferences(
            "TRACK_RECORD",
            Context.MODE_PRIVATE)
        return mSharedPreferences.getString("token","")
    }

    fun putToken(token:String){
        val mSharedPreferences = CusApplication.instance().getSharedPreferences("TRACK_RECORD", Context.MODE_PRIVATE)
        mSharedPreferences?.edit()?.putString("token", token)?.apply()
    }

    fun getToken(@ApplicationContext context: Context): String?{
        val mSharedPreferences = context.getSharedPreferences(
            "TRACK_RECORD",
            Context.MODE_PRIVATE)
        return mSharedPreferences.getString("token","")
    }

    fun getShareper(@ApplicationContext context: Context) : SharedPreferences{
        val mSharedPreferences = context.getSharedPreferences(
            "TRACK_RECORD",
            Context.MODE_PRIVATE)
        return mSharedPreferences
    }


}