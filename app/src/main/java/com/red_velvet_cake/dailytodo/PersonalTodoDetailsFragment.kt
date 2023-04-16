package com.red_velvet_cake.dailytodo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoDetailsBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment


class PersonalTodoDetailsFragment() : BaseFragment<FragmentPersonalTodoDetailsBinding>() {

    private var personalTodo: PersonalTodo? = null
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoDetailsBinding
        get() = FragmentPersonalTodoDetailsBinding::inflate


    override fun setUp() {
        personalTodo = arguments?.getParcelable(KEY_PERSONAL_TODO)
        binding.apply {
            textViewTodoTitle.text = personalTodo?.title
            textViewTodoCreationTime.text = personalTodo?.creationTime
            textViewTodoDetails.text = personalTodo?.description
        }
    }

    override fun addCallBacks() {

    }

    companion object {
        private const val KEY_PERSONAL_TODO = "PersonalTodo"
        fun newInstance(personalTodo: PersonalTodo) =
            PersonalTodoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_PERSONAL_TODO, personalTodo)
                }

            }
    }

}