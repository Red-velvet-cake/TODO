package com.red_velvet_cake.dailytodo.ui.fragment.register

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.BuildConfig
import com.red_velvet_cake.dailytodo.data.remote.service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {
        TodoServiceImpl().register(
            "unique2",
            "test123456",
            BuildConfig.TEAM_ID,
        ) {
            Log.d(TAG, "In callback")
            Log.d(TAG, "message: ${it.message}")
            Log.d(TAG, "isSuccess: ${it.isSuccess}")
            Log.d(TAG, "value: ${it.value.toString()}")
        }
    }

    override fun addCallBacks() {}

    companion object {
        const val TAG = "RegisterFragment"
    }

}