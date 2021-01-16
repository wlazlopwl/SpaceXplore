package com.appdevpwl.spacex.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

class SnackbarType {

    companion object {
        fun enableSnackbar(view:View, message: String ){
            val snackbar: Snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            snackbar.show()
        }
    }


}