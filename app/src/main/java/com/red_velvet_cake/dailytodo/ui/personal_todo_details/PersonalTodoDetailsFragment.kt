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
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.navigateBack


class PersonalTodoDetailsFragment : BaseFragment<FragmentPersonalTodoDetailsBinding>(),
    PersonalTodoDetailsView, PopupMenu.OnMenuItemClickListener {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    private val personalTodoDetailsPresenter = PersonalTodoDetailsPresenter(this)
    private lateinit var popupMenu: PopupMenu
    private lateinit var personalTodo: PersonalTodo

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun setUp() {
        setArgs()
        binding.apply {
            textViewTodoTitle.text = personalTodo.title
            textViewCreationDate.text = personalTodo.creationTime
            textViewTodoDescription.text = personalTodo.description
        }
        setupPopupMenu()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setArgs() {
        val bundle = this.arguments
        bundle?.getParcelable(DETAILS_PARAM, PersonalTodo::class.java)?.let {
            personalTodo = it
        }
    }


    private fun setupPopupMenu() {
        popupMenu = PopupMenu(requireContext(), binding.imageButtonShowFilterOptions)
        popupMenu.inflate(R.menu.popup_menu)
    }

    override fun addCallBacks() {
        popupMenu.setOnMenuItemClickListener(this)
        binding.imageButtonShowFilterOptions.setOnClickListener {
            popupMenu.show()
        }

        binding.toolbar.setNavigationOnClickListener { requireActivity().navigateBack() }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        Log.d("alhamsssss", "onMenuItemClick: My glasses")
        when (item?.itemId) {
            R.id.todo -> {
                personalTodoDetailsPresenter.updatePersonalTodoStatus(personalTodo.id, TODO_STATUS)
                return true
            }

            R.id.in_progress -> {
                Log.d("alhamsssss", "onMenuItemClick: My glassesesssssss harry potter")
                personalTodoDetailsPresenter.updatePersonalTodoStatus(
                    personalTodo.id,
                    IN_PROGRESS_STATUS,
                )
                return true
            }

            R.id.done -> {
                personalTodoDetailsPresenter.updatePersonalTodoStatus(personalTodo.id, DONE_STATUS)
                return true
            }

            else -> return false
        }
    }


    override fun showTodoStatusUpdatedFailure(errorMessage: String) {
        requireActivity().runOnUiThread {
            makeToast(errorMessage)
        }
    }

    private fun makeToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val DETAILS_PARAM = "personal todo details"
        private const val TODO_STATUS = 0
        private const val IN_PROGRESS_STATUS = 1
        private const val DONE_STATUS = 2
        fun newInstance(details: PersonalTodo) =
            PersonalTodoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_PARAM, details)
                }
            }
    }
}