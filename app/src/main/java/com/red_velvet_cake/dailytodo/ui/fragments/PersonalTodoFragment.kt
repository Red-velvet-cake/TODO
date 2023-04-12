package com.red_velvet_cake.dailytodo.ui.fragments


import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.presenter.MainPresenter
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoBinding
import com.red_velvet_cake.dailytodo.presenter.IMainView
import com.red_velvet_cake.dailytodo.model.UpdatePersonalStatusResponse
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import java.io.IOException


class PersonalTodoFragment : BaseFragment<FragmentPersonalTodoBinding>(), IMainView {
    private lateinit var mainPresenter: MainPresenter
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding =
        FragmentPersonalTodoBinding::inflate


    override fun setUp() {
        mainPresenter = MainPresenter(this)
        mainPresenter.updatePersonalTodoStatus("1", 1)
    }

    override fun addCallBacks() {
    }

    override fun onUpdatePersonalTodoStatusSuccess(updatePersonalStatusResponse: UpdatePersonalStatusResponse) {
    }

    override fun onUpdatePersonalTodoStatusFailure(e: IOException) {
    }

}