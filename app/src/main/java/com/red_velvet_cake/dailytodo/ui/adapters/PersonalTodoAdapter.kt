package com.red_velvet_cake.dailytodo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.databinding.ItemPersonalTodoBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseAdapter

class PersonalTodoAdapter:BaseAdapter<PersonalTodo,ItemPersonalTodoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemPersonalTodoBinding
        get() = ItemPersonalTodoBinding::inflate

    override fun bindItem(binding: ItemPersonalTodoBinding, item: PersonalTodo) {
        binding.apply {
            todoTitleTextview.text=item.title
            todoDescriptionTextview.text=item.description
            textviewTodoCreationDate.text=item.creationTime.split("T")[0]
            textviewTodoCreationTime.text=item.creationTime.split("T")[1].subSequence(0,5)
        }
    }

    override fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>,
    ): Boolean {
return (getOldItems()[oldItemPosition]==newItems[newItemPosition])
    }

}