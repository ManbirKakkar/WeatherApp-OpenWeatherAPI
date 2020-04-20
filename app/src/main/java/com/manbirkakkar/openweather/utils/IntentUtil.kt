package com.manbirkakkar.openweather.utils

import android.app.Activity
import android.content.Context
import android.content.Intent

class IntentUtil {
    companion object {
        /*Normal Intent*/
        fun moveNextScreen(source: Context, destination: Class<*>) {
            val intent = Intent(source, destination)
            intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
            source.startActivity(intent)

        }

        /*Intent with no back stack*/
        fun moveNextScreenWithNoStack(source: Context, destination: Class<*>) {
            val intent = Intent(source, destination)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION
            source.startActivity(intent)
        }

        fun moveScreenIntent(context: Context, cls: Class<*>, isFinish: Boolean?) {
            val `in` = Intent(context, cls)
            context.startActivity(`in`)
            (context as Activity).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            if (isFinish!!) {
                context.finish()
            }
        }
    }
}