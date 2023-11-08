package com.obody.health

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import com.obody.health.feature.WelcomeActivity

class App : Application() {

    var hot = false

    var startCount: Int = 0

    companion object {
        private lateinit var INSTANCE: App

        fun getInstance(): App {
            return INSTANCE
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            }

            override fun onActivityStarted(activity: Activity) {
                startCount++
                if (startCount == 1) {
                    if (activity !is WelcomeActivity) {
                        activity.startActivity(Intent(activity, WelcomeActivity::class.java))
                    }
                }
            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
                startCount--
            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }
        })
    }

}