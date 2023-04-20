package com.red_velvet_cake.dailytodo.ui.personal_todo_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.Constants
import com.red_velvet_cake.dailytodo.utils.navigateBack


class PersonalTodoDetailsFragment : BaseFragment<FragmentPersonalTodoDetailsBinding>(),
    PersonalTodoDetailsView, AdapterView.OnItemSelectedListener {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate

    private val personalTodoDetailsPresenter = PersonalTodoDetailsPresenter(this)
    private lateinit var personalTodo: PersonalTodo

    override fun setUp() {
        setArgs()
        initSpinner()
        binding.apply {
            textViewTodoTitle.text = personalTodo.title
            textViewCreationDate.text = personalTodo.creationTime
            textViewTodoDescription.text = personalTodo.description
        }
    }

    private fun initSpinner() {
        val status = listOf(
            getString(R.string.todo_status),
            getString(R.string.in_progress_status),
            getString(R.string.done_status)
        )
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, status)
        spinnerAdapter.setDropDownViewResource(R.layout.item_todo_type)

        binding.spinnerTodoStatus.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = this@PersonalTodoDetailsFragment
        }
    }

    private fun setArgs() {
        personalTodo = arguments?.getParcelable(DETAILS_PARAM)!!
    }

    override fun addCallBacks() {
        binding.toolbar.setNavigationOnClickListener { requireActivity().navigateBack() }
    }

    override fun showTodoStatusUpdatedFailure(errorMessage: String) {
        requireActivity().runOnUiThread {
            makeToast(errorMessage)
        }
    }

    override fun navigateBack() {
        requireActivity().navigateBack()
    }

    private fun makeToast(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val DETAILS_PARAM = "personal todo details"
        fun newInstance(details: PersonalTodo) =
            PersonalTodoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_PARAM, details)
                }
            }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            Constants.TODO -> {
                personalTodoDetailsPresenter.updatePersonalTodoStatus(
                    personalTodo.id,
                    Constants.TODO
                )
            }

            Constants.IN_PROGRESS -> {
                personalTodoDetailsPresenter.updatePersonalTodoStatus(
                    personalTodo.id,
                    Constants.IN_PROGRESS
                )
            }

            Constants.DONE -> {
                personalTodoDetailsPresenter.updatePersonalTodoStatus(
                    personalTodo.id,
                    Constants.DONE
                )
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(requireContext(), "Nothing Selected ", Toast.LENGTH_SHORT)
            .show()
    }
}