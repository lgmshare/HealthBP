package com.obody.health.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.obody.health.BuildConfig

class Utils {

    companion object {
        fun toast(context: Context, msg: String) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        fun toast(context: Context, resId: Int) {
            Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
        }

        fun log(msg: String) {
            if (BuildConfig.DEBUG) {
                Log.d("HealthBP", msg)
            }
        }
    }

}