package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.databinding.ItemStatisticsTasksPersonHasDoneBinding
import com.red_velvet_cake.dailytodo.databinding.ItemTodosSectionTitleBinding
import com.red_velvet_cake.dailytodo.databinding.ListPersonalAndTeamTasksBinding

class HomeAdapter(private val list: List<HomeItems<Any>>) :
    RecyclerView.Adapter<HomeAdapter.BaseHomeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeHolder {
        return when (viewType) {
            R.layout.item_statistics_tasks_person_has_done -> StaticsTasksPersonHasDoneHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_statistics_tasks_person_has_done,
                    parent,
                    false
                )
            )

            R.layout.item_todos_section_title -> TodosSectionTitleHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_todos_section_title,
                    parent,
                    false
                )
            )

            R.layout.list_personal_and_team_tasks -> PersonalTasksHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_statistics_tasks_person_has_done,
                    parent,
                    false
                )
            )

            R.layout.list_personal_and_team_tasks -> TeamTasksHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_statistics_tasks_person_has_done,
                    parent,
                    false
                )
            )

            else -> throw java.lang.Exception("Unknown home item type")
        }
    }


    override fun onBindViewHolder(holder: BaseHomeHolder, position: Int) {
        when (holder) {
            is StaticsTasksPersonHasDoneHolder -> holder.bind(list[position])
            is TodosSectionTitleHolder -> holder.bind(list[position])
            is PersonalTasksHolder -> holder.bind(list[position])
            is TeamTasksHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE -> R.layout.item_statistics_tasks_person_has_done
            HomeItemType.ITEM_TODOS_SECTION_TITLE -> R.layout.item_todos_section_title
            HomeItemType.LIST_PERSONAL_TASKS -> R.layout.item_personal_and_team_tasks
            else -> R.layout.item_personal_and_team_tasks
        }
    }

    abstract class BaseHomeHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item: HomeItems<Any>)
    }

    inner class StaticsTasksPersonHasDoneHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemStatisticsTasksPersonHasDoneBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            TODO("Not yet implemented")
        }

    }

    inner class TodosSectionTitleHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemTodosSectionTitleBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            fun bind(item: HomeItems<Any>) {
                val todoType = item.data as String
                binding.apply {
                    textViewTodoType.text = todoType
                }
            }
        }

    }

    inner class PersonalTasksHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ListPersonalAndTeamTasksBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val adapter = GetAllPersonalTodosAdapter(item.data as GetAllPersonalTodosResponse)
            binding.recyclerViewTodos.adapter = adapter
        }

    }

    inner class TeamTasksHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ListPersonalAndTeamTasksBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val adapter = GetAllTeamTodosAdapter(item.data as GetAllTeamTodosResponse)
            binding.recyclerViewTodos.adapter = adapter
        }

    }

}