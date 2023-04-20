package com.red_velvet_cake.dailytodo.ui.personal_todo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.LayoutPersonalTodoItemBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseAdapter

class PersonalTodoAdapter(
    private val onUpdatedStatus: (todoId: String, status: Int) -> Unit,
    private val onTodoClick: (personalTodo: PersonalTodo) -> Unit
) : BaseAdapter<PersonalTodo, LayoutPersonalTodoItemBinding>() {

    var selectedChipAdapter = -1
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> LayoutPersonalTodoItemBinding =
        LayoutPersonalTodoItemBinding::inflate

    override fun bindItem(binding: LayoutPersonalTodoItemBinding, item: PersonalTodo) {
        val (date, time) = extractDateAndTime(item.creationTime)
        with(binding) {
            todoTitleTextview.text = item.title
            todoDescriptionTextview.text = item.description
            textviewTodoCreationDate.text = date
            textviewTodoCreationTime.text = time
            root.setOnClickListener {
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