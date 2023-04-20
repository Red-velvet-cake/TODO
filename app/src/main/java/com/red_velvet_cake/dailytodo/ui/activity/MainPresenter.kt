package com.red_velvet_cake.dailytodo.ui.activity

import com.red_velvet_cake.dailytodo.data.local.LocalDataImpl

class MainPresenter(private val view: MainView) {

    private val localDataImpl = LocalDataImpl()

    fun checkLoggedInUser() {
        if (localDataImpl.getUserToken() == null) {
            view.navigateToLogin()
        } else {
            view.navigateToHome()
        }
    }
}