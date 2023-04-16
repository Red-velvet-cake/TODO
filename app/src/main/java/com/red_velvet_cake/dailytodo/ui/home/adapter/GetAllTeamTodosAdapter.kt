package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodoResponse
import com.red_velvet_cake.dailytodo.databinding.ItemPersonalAndTeamTasksBinding

class GetAllTeamTodosAdapter(private val allTodos: GetAllTeamTodosResponse) :
    RecyclerView.Adapter<GetAllTeamTodosAdapter.GetAllTeamTodosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetAllTeamTodosHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_personal_and_team_tasks, parent, false
        )

        return GetAllTeamTodosHolder(view)
    }


    override fun onBindViewHolder(holder: GetAllTeamTodosHolder, position: Int) {
        val todo = allTodos.value[position]
        holder.bind(todo)
    }

    override fun getItemCount() = allTodos.value.size

    inner class GetAllTeamTodosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPersonalAndTeamTasksBinding.bind(itemView)

        fun bind(teamTodoResponse: TeamTodoResponse) {
            val creationTime = teamTodoResponse.creationTime.split("T")
            binding.apply {
                textViewTodoTitle.text = teamTodoResponse.title
                textViewDescriptionTodo.text = teamTodoResponse.description
                textViewAssignment.text = teamTodoResponse.assignee
                textViewUpdateDate.text = creationTime[0]
                textViewTime.text = creationTime[1]
            }
        }
    }
}