package com.red_velvet_cake.dailytodo.ui.activity

import com.red_velvet_cake.dailytodo.data.local.SharedPrefs

class MainPresenter(private val view: MainView) {

    fun checkLoggedInUser() {
        if (SharedPrefs.token == null) {
            view.navigateToLogin()
        } else {
            view.navigateToHome()
        }
    }

}