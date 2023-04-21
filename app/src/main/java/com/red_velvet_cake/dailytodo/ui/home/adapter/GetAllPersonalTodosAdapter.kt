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
import com.red_velvet_cake.dailytodo.utils.Constants.HOME_TODO_COUNT

class GetAllPersonalTodosAdapter(
    private val todos: GetAllPersonalTodosResponse,
    private val onClickPersonalTodo: (PersonalTodo) -> Unit,
) :
    RecyclerView.Adapter<GetAllPersonalTodosAdapter.GetAllPersonalTodosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllPersonalTodosHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_personal_todo, parent, false
        )
        return GetAllPersonalTodosHolder(view)
    }


    override fun onBindViewHolder(holder: GetAllPersonalTodosHolder, position: Int) {
        val todo = todos.value.reversed().take(HOME_TODO_COUNT)[position]
        holder.bind(todo)
        holder.binding.root.setOnClickListener {
            onClickPersonalTodo(todo)
        }
    }

    override fun getItemCount() = todos.value.take(HOME_TODO_COUNT).size

    inner class GetAllPersonalTodosHolder(itemView: View) : ViewHolder(itemView) {
        val binding = ItemPersonalTodoBinding.bind(itemView)

        fun bind(personalTodo: PersonalTodo) {
            val (date, time) = extractDateAndTime(personalTodo.creationTime)
            binding.apply {
                textViewTodoTitle.text = personalTodo.title
                textViewDescriptionTodo.text = personalTodo.description
                textViewUpdateDate.text = date
                textViewTime.text = time
            }
        }
    }

    private fun extractDateAndTime(dateTimeString: String): Pair<String, String> {
        val parts = dateTimeString.split("T")
        return Pair(parts[0], parts[1].substring(0, 5))
    }
}