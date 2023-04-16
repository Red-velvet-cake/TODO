package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.ItemTeamTodoBinding

class GetAllTeamTodosAdapter(private val todos: GetAllTeamTodosResponse) :
    RecyclerView.Adapter<GetAllTeamTodosAdapter.GetAllTeamTodosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllTeamTodosHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_team_todo, parent, false
        )

        return GetAllTeamTodosHolder(view)
    }


    override fun onBindViewHolder(holder: GetAllTeamTodosHolder, position: Int) {
        val todo = todos.value[position]
        holder.bind(todo)
    }

    override fun getItemCount() = todos.value.size

    inner class GetAllTeamTodosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTeamTodoBinding.bind(itemView)

        fun bind(teamTodo: TeamTodo) {
            val creationTime = teamTodo.creationTime.split("T")
            binding.apply {
                textViewTodoTitle.text = teamTodo.title
                textViewDescriptionTodo.text = teamTodo.description
                textViewAssignment.text = teamTodo.assignee
                textViewUpdateDate.text = creationTime[0]
                textViewTime.text = creationTime[1]
            }
        }
    }
}