package  com.manbirkakkar.openweather.base

import android.app.Activity


object SessionClass {
    private const val PREF = "PREF"

    fun setUnits(activity: Activity, unit: String) {
        val preferences = activity.getSharedPreferences(PREF, 0)
        val editor = preferences.edit()

        editor.putString(PREF, unit)
        editor.commit()
    }

    fun getUnit(activity: Activity): String {
        val preferences = activity.getSharedPreferences(PREF, 0)
        return preferences.getString(PREF, Constant.UNITS_METRIC)!!
    }



}