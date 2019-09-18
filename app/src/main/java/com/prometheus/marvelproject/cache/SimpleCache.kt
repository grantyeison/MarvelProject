package com.prometheus.marvelproject.cache

import android.content.Context
import android.content.SharedPreferences
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import org.json.JSONObject
import java.security.NoSuchAlgorithmException
import java.util.HashSet


class SimpleCache(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        setValue("PUBLIC_KEY","135a6820831564dd6d502eeab3d8e3d2")
        setValue("PRIVATE_KEY","4029e7dee7cc0c56f6cd98431f5b3e219628e91f")
    }

    fun setValue(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun setValue(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun setValue(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun setValue(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun setValue(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun setValue(key: String, value: Set<String>) {
        sharedPreferences.edit().putStringSet(key, value).apply()
    }

    fun getLongValue(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }

    fun getIntValue(key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun getBooleanValue(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getStringValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun getSetValue(key: String): MutableSet<String>? {
        return sharedPreferences.getStringSet(key, HashSet<String>())
    }

    fun getFloatValue(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun getJsonValue(key: String): JSONObject {
        val jsonArray = sharedPreferences.getString(key, null)
        val json: JSONObject = JSONObject(jsonArray)
        return json
    }
    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun contains(key: String): Boolean{
        return sharedPreferences.contains(key)
    }

    fun clear(): Boolean {
        return sharedPreferences.edit().clear().commit()
    }

    companion object {
        private const val PREF_NAME = "pref_codes"
    }


    fun md5(s: String): String {
        return String(Hex.encodeHex(DigestUtils.md5(s)))

    }
}
