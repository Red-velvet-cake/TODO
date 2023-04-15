package com.red_velvet_cake.dailytodo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.databinding.FragmentPersonalTodoBinding
import com.red_velvet_cake.dailytodo.presenter.get_all_personal_todo.GetAllPersonalTodosPresenter
import com.red_velvet_cake.dailytodo.presenter.get_all_personal_todo.GetAllPersonalTodosView
import com.red_velvet_cake.dailytodo.ui.adapters.PersonalTodoAdapter
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.utils.ItemTouchHelperCallback
import java.io.IOException

class PersonalTodosFragment:BaseFragment<FragmentPersonalTodoBinding>(),GetAllPersonalTodosView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPersonalTodoBinding=FragmentPersonalTodoBinding::inflate
    override fun setUp() {
        val presenter=GetAllPersonalTodosPresenter(this)
         presenter.getAllPersonalTodos()

    }

    override fun addCallBacks() {
    }

    override fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        val adapter= PersonalTodoAdapter()
        adapter.submitList(getAllPersonalTodosResponse.value)

        val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)

        requireActivity().runOnUiThread{
            binding.recyclerPersonalTodo.adapter=adapter
            itemTouchHelper.attachToRecyclerView(binding.recyclerPersonalTodo)
        }
    }

    override fun onGetAllPersonalTodosFailure(e: IOException) {
        Toast.makeText(requireContext(),e.message,Toast.LENGTH_LONG).show()
    }
}