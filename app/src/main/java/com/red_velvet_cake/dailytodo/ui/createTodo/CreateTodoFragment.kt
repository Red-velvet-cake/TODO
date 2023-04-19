package com.red_velvet_cake.dailytodo.ui.createTodo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.CreateTodoPersonalResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentCreateTeamTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import okio.IOException

class CreateTodoFragment() : BaseFragment<FragmentCreateTeamTodoBinding>(), CreateTodoView {
    private val presenter = CreateTodoPresenter(this)
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCreateTeamTodoBinding
        get() = FragmentCreateTeamTodoBinding::inflate

    override fun setUp() {}

    override fun addCallBacks() {
    }

    override fun onCreateTeamTodoFailure(e: IOException) {
        TODO("Not yet implemented")
    }

    override fun onCreatePersonalTodoSuccess(createTodoPersonalResponse: CreateTodoPersonalResponse) {
        TODO("Not yet implemented")
    }

    override fun onCreatePersonalTodoFailure(e: IOException) {
        TODO("Not yet implemented")
    }

    override fun showCreateSuccessMessage() {
        TODO("Not yet implemented")
    }

    override fun enableCreateButton() {
        TODO("Not yet implemented")
    }

    override fun showCreateFailedMessage(message: String) {
        TODO("Not yet implemented")
    }

}