package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.ItemPersonalTodoBinding

class GetAllPersonalTodosAdapter(private val todos: GetAllPersonalTodosResponse) :
    RecyclerView.Adapter<GetAllPersonalTodosAdapter.GetAllPersonalTodosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllPersonalTodosHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_personal_todo, parent, false
        )

        return GetAllPersonalTodosHolder(view)
    }


    override fun onBindViewHolder(holder: GetAllPersonalTodosHolder, position: Int) {
        val todo = todos.value[position]
        holder.bind(todo)
    }

    override fun getItemCount() = todos.value.size

    inner class GetAllPersonalTodosHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = ItemPersonalTodoBinding.bind(itemView)

        fun bind(personalTodo: PersonalTodo) {
            val creationTime = personalTodo.creationTime.split('T')
            binding.apply {
                textViewTodoTitle.text = personalTodo.title
                textViewDescriptionTodo.text = personalTodo.description
                textViewUpdateDate.text = creationTime[0]
                textViewTime.text = creationTime[1]
            }
        }
    }
}