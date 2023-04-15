package com.red_velvet_cake.dailytodo.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.red_velvet_cake.dailytodo.ui.adapters.PersonalTodoAdapter
import java.util.*

class ItemTouchHelperCallback(
    private val adapter: PersonalTodoAdapter,
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPosition = viewHolder.layoutPosition
        val toPosition = target.layoutPosition
        Collections.swap(adapter.getOldItems(), fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.layoutPosition
        when(direction)
        {
            ItemTouchHelper.LEFT->adapter.swipedLeft(position)
            ItemTouchHelper.RIGHT->adapter.swipedRight(position)
        }

    }

}