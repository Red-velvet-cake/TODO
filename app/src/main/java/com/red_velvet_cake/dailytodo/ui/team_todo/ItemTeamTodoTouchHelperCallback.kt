package com.red_velvet_cake.dailytodo.ui.team_todo

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ItemTeamTodoTouchHelperCallback(
    private val teamTodoAdapter: TeamToDoAdapter,
) : ItemTouchHelper.SimpleCallback(
    0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPosition = viewHolder.layoutPosition
        val toPosition = target.layoutPosition
        Collections.swap(teamTodoAdapter.getOldItems(), fromPosition, toPosition)
        teamTodoAdapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.layoutPosition
        when (direction) {
            ItemTouchHelper.LEFT -> teamTodoAdapter.swipedLeft(position)
            ItemTouchHelper.RIGHT -> teamTodoAdapter.swipedRight(position)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        val itemView = viewHolder.itemView
        var background: ColorDrawable
        val backgroundCornerOffset = itemView.width
        if (dX > 0) {
            val text = when (teamTodoAdapter.selectedChipAdapter) {
                1 -> {
                    "To Do"
                }
                0 -> {
                    "Done"
                }
                2 -> {
                    "In progress"
                }
                else -> {
                    ""
                }
            }
            background = ColorDrawable(Color.parseColor("#7B61FF"))
            drawTextAndBackground(c, itemView, background, text, dX, backgroundCornerOffset)
        } else if (dX < 0) {
            val text = when (teamTodoAdapter.selectedChipAdapter) {
                1 -> {
                    "Done"
                }
                0 -> {
                    "In progress"
                }
                2 -> {
                    "To do"
                }
                else -> {
                    ""
                }
            }
            background = ColorDrawable(Color.parseColor("#7B61FF"))
            drawTextAndBackground(c, itemView, background, text, dX, backgroundCornerOffset)
        }

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    private fun drawTextAndBackground(
        c: Canvas,
        itemView: View,
        background: ColorDrawable,
        text: String,
        dX: Float,
        backgroundCornerOffset: Int
    ) {

        val background = GradientDrawable()
        background.setColor(Color.parseColor("#E5DFFF"))
        val radius = 16f
        background.cornerRadii = floatArrayOf(
            radius, radius, radius, radius, radius, radius, radius, radius
        )
        background.setBounds(itemView.left, itemView.top, itemView.right, itemView.bottom)
        background.draw(c)


        val paint = Paint()
        paint.color = Color.parseColor("#7B61FF")
        paint.isAntiAlias = true
        paint.textSize = 40f

        val textWidth = paint.measureText(text)
        val textX = if (dX > 0) {
            itemView.left + 100f
        } else {
            itemView.right - textWidth - 100f
        }
        val textY = (itemView.height + paint.textSize) / 2
        c.drawText(text, itemView.left + textX, itemView.top + textY, paint)
    }
}