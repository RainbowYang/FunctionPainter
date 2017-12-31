package rainbow.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun Any?.toJsonWhenExpose() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(this)!!

fun Any?.toJson() = Gson().toJson(this)!!

inline fun <reified T> String.fromJson() = Gson().fromJson<T>(this, T::class.java)!!

fun Any?.println() = println(this)