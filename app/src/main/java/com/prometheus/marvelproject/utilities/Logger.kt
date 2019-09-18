package com.prometheus.marvelproject.utilities


import android.content.Context
import android.os.Bundle
import android.util.Log

class Logger(context: Context) {

    private val sb: StringBuilder = StringBuilder(255)
    //   private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)

    private fun getTag(depth: LOGGER_DEPTH): String? {
        try {
            val className = Thread.currentThread().stackTrace[depth.value].className
            sb.append(className.substring(className.lastIndexOf(".") + 1))
            sb.append("[")
            sb.append(Thread.currentThread().stackTrace[depth.value].methodName)
            sb.append("] - ")
            sb.append(Thread.currentThread().stackTrace[depth.value].lineNumber)
            return sb.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Log.d(personalTAG, ex.message)
        } finally {
            sb.setLength(0)
        }
        return null
    }

    fun d(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.d(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun d(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.d(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun d(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.d(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun e(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.e(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun e(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.e(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun e(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.e(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun w(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.w(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun w(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.w(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun w(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.w(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun v(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.v(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun v(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.v(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun v(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.v(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun i(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.i(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun i(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.i(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun i(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.i(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun wtf(msg: String) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.wtf(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun wtf(msg: String, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.wtf(tag, msg)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun wtf(msg: String, t: Throwable, depth: LOGGER_DEPTH) {
        val tag = getTag(depth)
        try {
            Log.wtf(tag, msg, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }

    fun wtf(t: Throwable) {
        val tag = getTag(LOGGER_DEPTH.ACTUAL_METHOD)
        try {
            Log.wtf(tag, t.message, t)
        } catch (exception: Exception) {
            Log.e(getTag(LOGGER_DEPTH.ACTUAL_METHOD), "Logger failed, exception: " + exception.message)
        }

    }
    enum class LOGGER_DEPTH(val value: Int) {
        ACTUAL_METHOD(4),
        LOGGER_METHOD(3),
        STACK_TRACE_METHOD(1),
        JVM_METHOD(0)
    }

    companion object {
        private val personalTAG = "Logger"
    }
}
