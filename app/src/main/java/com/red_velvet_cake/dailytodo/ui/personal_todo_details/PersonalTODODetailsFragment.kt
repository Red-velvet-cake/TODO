package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.remote.TodoService
import com.red_velvet_cake.dailytodo.data.remote.TodoServiceImpl
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.RequestStatus
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import kotlin.math.log


class PersonalTODODetailsFragment : BaseFragment<FragmentPersonalTodoDetailsBinding>(),
    PersonalTodoStatus, PopupMenu.OnMenuItemClickListener {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    private lateinit var presenter: PersonalTODOStatusPresenter
    private lateinit var todoService: TodoService
    private lateinit var popupMenu: PopupMenu
    private lateinit var details: PersonalTodo

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setUp() {
        showTodoDetails()
        setupInjection()
        popupMenu()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun showTodoDetails() {
        val bundle = this.arguments
        bundle?.getParcelable(DETAILS_PARAM, PersonalTodo::class.java)?.let {
            details = it
        }
    }

    private fun setupInjection() {
        todoService = TodoServiceImpl()
        presenter = PersonalTODOStatusPresenter(this, todoService)
    }

    private fun popupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.imageButtonShowMenu)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.setOnMenuItemClickListener(this)
    }

    override fun addCallBacks() {
        binding.imageButtonShowMenu.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun makeToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.todo -> {
                presenter.setTodoStatus(TodoStatus.Todo, "details.id")
                return true
            }

            R.id.in_progress -> {
                presenter.setTodoStatus(TodoStatus.InProgress, "details.id")
                return true
            }

            R.id.done -> {
                presenter.setTodoStatus(TodoStatus.Done, "details.id")
                return true
            }

            else -> return false
        }
    }

    override fun handleRequestStatus(status: RequestStatus) {
        when (status) {
            is RequestStatus.Loading -> handleProgress(true)
            is RequestStatus.Success -> handleProgress(false)
            is RequestStatus.Error -> {
                handleProgress(false)
                requireActivity().runOnUiThread {
                    makeToast(status.message)
                }
            }
        }
    }

    private fun handleProgress(visibility: Boolean) {
        requireActivity().runOnUiThread {
            binding.progress.isVisible = visibility
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