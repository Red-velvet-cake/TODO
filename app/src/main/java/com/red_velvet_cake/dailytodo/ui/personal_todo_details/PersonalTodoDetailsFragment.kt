package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.TodoStatus
import java.io.IOException


class PersonalTodoDetailsFragment : BaseFragment<FragmentPersonalTodoDetailsBinding>(),
    PersonalTodoDetailsView, PopupMenu.OnMenuItemClickListener {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    private val presenterTodoStatus = PersonalTodoDetailsPresenter(this)
    private lateinit var popupMenu: PopupMenu
    private lateinit var details: PersonalTodo

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setUp() {
        setArgs()
        setupPopupMenu()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setArgs() {
        val bundle = this.arguments
        bundle?.getParcelable(DETAILS_PARAM, PersonalTodo::class.java)?.let {
            details = it
        }
    }


    private fun setupPopupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.imageButtonShowMenu)
        popupMenu.inflate(R.menu.popup_menu)
    }

    override fun addCallBacks() {
        popupMenu.setOnMenuItemClickListener(this)
        binding.imageButtonShowMenu.setOnClickListener {
            popupMenu.show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.todo -> {
                presenterTodoStatus.setTodoStatus(TodoStatus.Todo, "details.id")
                return true
            }

            R.id.in_progress -> {
                presenterTodoStatus.setTodoStatus(TodoStatus.InProgress, "details.id")
                return true
            }

            R.id.done -> {
                presenterTodoStatus.setTodoStatus(TodoStatus.Done, "details.id")
                return true
            }

            else -> return false
        }
    }

    override fun showTodoStatusUpdatedLoading(isLoading: Boolean) {
        requireActivity().runOnUiThread {
            setProgressBarVisibility(isLoading)
        }
    }

    override fun showTodoStatusUpdatedSuccessfully() {
        requireActivity().runOnUiThread {
            makeToast(getString(R.string.update_succeeded))
        }
    }

    override fun showTodoStatusUpdatedFailure(exception: IOException) {
        requireActivity().runOnUiThread {
            makeToast(exception.message.toString())
        }
    }

    private fun setProgressBarVisibility(visibility: Boolean) {
        binding.progress.isVisible = visibility
    }

    private fun makeToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private companion object {
        private const val DETAILS_PARAM = "personal todo details"
        fun newInstance(details: PersonalTodo) =
            PersonalTodoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_PARAM, details)
                }
            }
    }
}