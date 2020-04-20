package com.manbirkakkar.openweather.utils

import android.content.Context
import android.graphics.PorterDuff
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat


object AlertUtil {

    fun showToast(context: Context, msg: CharSequence) {
        showCustomToast(context, msg, Toast.LENGTH_LONG)
    }

    fun showCustomToast(context: Context, msg: CharSequence, duration: Int) {
        val toast = Toast.makeText(context, msg, duration)
        val rootView = toast.view

        val color = ContextCompat.getColor(context, android.R.color.darker_gray)
        rootView.background.setColorFilter(color, PorterDuff.Mode.SRC_IN)

        val textView = rootView.findViewById<TextView>(android.R.id.message)
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white))

        toast.show()
    }

    fun showErrorDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle("$title")
            .setMessage("$message")

            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.ok, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


    fun showDialogSingleMessage(context: Context) {
        AlertDialog.Builder(context)
            .setTitle("No Devices Found")
            .setMessage("There are no unallocated devices present at the moment. Please try later")

            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                dialog.cancel()
            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

}
