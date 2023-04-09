package com.red_velvet_cake.dailytodo.ui.fragment.register

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.databinding.FragmentRegisterBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {}

}