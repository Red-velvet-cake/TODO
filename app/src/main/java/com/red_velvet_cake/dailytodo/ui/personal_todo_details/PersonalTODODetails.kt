package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment


class PersonalTODODetails : BaseFragment<FragmentPersonalTodoDetailsBinding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setUp() {
        val bundle = this.arguments
        val details = bundle!!.getParcelable(DETAILS_PARAM, PersonalTodo::class.java)
    }

    override fun addCallBacks() {
        TODO("Not yet implemented")
    }

    private companion object {
        private const val DETAILS_PARAM = "personal todo details"
        fun newInstance(details: PersonalTodo) =
            PersonalTODODetails().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_PARAM, details)
                }
            }
    }
}