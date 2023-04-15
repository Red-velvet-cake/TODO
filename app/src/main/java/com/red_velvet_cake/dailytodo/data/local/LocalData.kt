package com.red_velvet_cake.dailytodo.data.local

import com.orhanobut.hawk.Hawk

object LocalData {

    operator fun <T> set(key: String?, value: T) {
        Hawk.put(key, value)
    }

    operator fun <T> get(key: String?, defaultValue: T? = null): T {
        return if (defaultValue != null) Hawk.get(key, defaultValue) else Hawk.get(key)
    }

}