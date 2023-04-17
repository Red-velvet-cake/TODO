package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment


class PersonalTODODetailsFragment : BaseFragment<FragmentPersonalTodoDetailsBinding>(),
    PersonalTodoStatus {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    private lateinit var presenter: PersonalTODOStatusPresenter
    private lateinit var todoService: TodoService

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setUp() {
        setupInjection()
        showTodoDetails()
    }

    override fun addCallBacks() {
        setTodoStatus()
    }

    private fun setupInjection() {
        todoService = TodoServiceImpl()
        presenter = PersonalTODOStatusPresenter(this, todoService)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showTodoDetails() {
        val bundle = this.arguments
        bundle?.getParcelable(DETAILS_PARAM, PersonalTodo::class.java)
    }

    private fun setTodoStatus() {

    }

    override fun showLoading(status: Boolean) {

    }

    override fun showError(errorMessage: String) {
//        makeToast(errorMessage)
    }

    private fun makeToast(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private companion object {
        private const val DETAILS_PARAM = "personal todo details"
        fun newInstance(details: PersonalTodo) =
            PersonalTODODetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_PARAM, details)
                }
            }
    }
}