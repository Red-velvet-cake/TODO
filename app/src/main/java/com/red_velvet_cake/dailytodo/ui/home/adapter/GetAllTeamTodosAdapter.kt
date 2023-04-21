package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.ItemTeamTodoBinding
import com.red_velvet_cake.dailytodo.utils.Constants.HOME_TODO_COUNT

class GetAllTeamTodosAdapter(
    private val todos: GetAllTeamTodosResponse,
    private val onClickTeamTodo: (TeamTodo) -> Unit,
) :
    RecyclerView.Adapter<GetAllTeamTodosAdapter.GetAllTeamTodosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllTeamTodosHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_team_todo, parent, false
        )

        return GetAllTeamTodosHolder(view)
    }


    override fun onBindViewHolder(holder: GetAllTeamTodosHolder, position: Int) {
        val todo = todos.value.reversed().take(HOME_TODO_COUNT)[position]
        holder.bind(todo)
        holder.binding.root.setOnClickListener { onClickTeamTodo(todo) }

    }

    override fun getItemCount() = todos.value.take(HOME_TODO_COUNT).size

    inner class GetAllTeamTodosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTeamTodoBinding.bind(itemView)

        fun bind(teamTodo: TeamTodo) {
            val (date, time) = extractDateAndTime(teamTodo.creationTime)
            binding.apply {
                textViewTodoTitle.text = teamTodo.title
                textViewDescriptionTodo.text = teamTodo.description
                textViewAssignment.text = teamTodo.assignee
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