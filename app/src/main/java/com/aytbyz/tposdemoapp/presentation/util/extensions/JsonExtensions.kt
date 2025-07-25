package com.aytbyz.tposdemoapp.presentation.util.extensions

import com.google.gson.Gson

inline fun <reified T> T.toJson(): String {
    return Gson().toJson(this)
}