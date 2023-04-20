package com.red_velvet_cake.dailytodo.ui.team_todo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.LayoutTeamTodoItemBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseAdapter

class TeamTodoAdapter(
    private val onUpdatedStatus: (String, Int) -> Unit,
    private val onTodoClick: (TeamTodo) -> Unit
) : BaseAdapter<TeamTodo, LayoutTeamTodoItemBinding>() {

    var selectedChipAdapter = -1
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutTeamTodoItemBinding =
        LayoutTeamTodoItemBinding::inflate

    override fun bindItem(binding: LayoutTeamTodoItemBinding, item: TeamTodo) {
        val (date, time) = extractDateAndTime(item.creationTime)
        with(binding) {
            textviewTodoAssigneeName.text = item.assignee.toString()
            todoTitleTextview.text = item.title
            todoDescriptionTextview.text = item.description
            textviewTodoCreationDate.text = date
            textviewTodoCreationTime.text = time
            root.setOnClickListener {
                Log.d("sadeq", "clicked")
                onTodoClick(item)
            }
        }
    }

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ): Boolean {
        return (getOldItems()[oldItemPosition] == newItems[newItemPosition])
    }

    fun swipedLeft(position: Int) {
        when (selectedChipAdapter) {
            0 -> {
                onUpdatedStatus(getOldItems()[position].id, 1)
                getOldItems()[position].status = 1
            }

            1 -> {
                onUpdatedStatus(getOldItems()[position].id, 2)
                getOldItems()[position].status = 2
            }

            2 -> {
                onUpdatedStatus(getOldItems()[position].id, 0)
                getOldItems()[position].status = 0
            }
        }
        submitList(getOldItems().filter { it.status == selectedChipAdapter })
    }

    fun swipedRight(position: Int) {
        when (selectedChipAdapter) {
            0 -> {
                onUpdatedStatus(getOldItems()[position].id, 2)
                getOldItems()[position].status = 2
            }

            1 -> {
                onUpdatedStatus(getOldItems()[position].id, 0)
                getOldItems()[position].status = 0
            }

            2 -> {
                onUpdatedStatus(getOldItems()[position].id, 1)
                getOldItems()[position].status = 1
            }
        }
        submitList(getOldItems().filter { it.status == selectedChipAdapter })
    }


    private fun extractDateAndTime(dateTimeString: String): Pair<String, String> {
        val parts = dateTimeString.split("T")
        return Pair(parts[0], parts[1].substring(0, 5))
    }

    fun setSelectedChip(selectedChip: Int) {
        this.selectedChipAdapter = selectedChip
    }

}