package com.red_velvet_cake.dailytodo.ui.create_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.chip.Chip
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.databinding.FragmentCreateTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment

class CreateTodoFragment() : BaseFragment<FragmentCreateTeamTodoBinding>(), CreateTodoView {
    private val presenter = CreateTodoPresenter(this)
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateTeamTodoBinding
        get() = FragmentCreateTeamTodoBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {
        setupCreateButtonClickListener()
        setupTypeTodoCreated()
    }

    private fun setupTypeTodoCreated() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->

            if (checkedIds.size != 0) {
                val chip: Chip? = group.findViewById(checkedIds[0])
                chip?.let {
                    if (it.text.toString() == getString(R.string.personalButton)) {
                        binding.textName.visibility = View.GONE
                        binding.buttonTeam.isClickable = true
                        binding.buttonPersonal.isClickable = false

                    } else if (it.text.toString() == getString(R.string.teamButton)) {
                        binding.textName.visibility = View.VISIBLE
                        binding.buttonTeam.isClickable = false
                        binding.buttonPersonal.isClickable = true
                    }
                }
            }

        }
    }

    private fun setupCreateButtonClickListener() {
        binding.buttonCreate.setOnClickListener {
            val title = binding.titleText.text.toString()
            val description = binding.textDescription.text.toString()
            val name = binding.textName.text.toString()
            when (binding.chipGroup.checkedChipId) {
                R.id.buttonPersonal -> {
                    presenter.clickCreateTodoPersonalButton(title, description)
                }
                R.id.buttonTeam -> {
                    presenter.clickCreateTodoTeamButton(title, description, name)
                }
            }
            binding.titleText.setText("")
            binding.textDescription.setText("")
            binding.textName.setText("")
        }
    }

    override fun onCreateTeamTodoFailure(errorMessage: String) {
        showToast(errorMessage)
    }

    override fun onCreatePersonalTodoFailure(errorMessage: String) {
        showToast(errorMessage)
    }

    override fun showCreateSuccessMessage() {
        showToast(getString(R.string.create_Todo_success))
    }

    override fun enableCreateButton() {
        requireActivity().runOnUiThread {
            binding.apply {
                buttonCreate.isEnabled = true
                progressBarLoad.visibility = View.GONE
            }
        }
    }

    override fun showCreateFailedMessage(errorMessage: String) {
        showToast(errorMessage)
    }

    override fun disableCreateButtonWithLoading() {
        requireActivity().runOnUiThread {
            binding.apply {
                buttonCreate.isEnabled = false
                progressBarLoad.visibility = View.VISIBLE
            }
        }
    }

    private fun showToast(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}