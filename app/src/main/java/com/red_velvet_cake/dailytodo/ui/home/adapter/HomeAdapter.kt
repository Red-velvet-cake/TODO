package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.Statistics
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.ItemStatisticsTasksPersonHasDoneBinding
import com.red_velvet_cake.dailytodo.databinding.ItemTodosSectionTitleBinding
import com.red_velvet_cake.dailytodo.databinding.ListPersonalTodosBinding
import com.red_velvet_cake.dailytodo.databinding.ListTeamTodosBinding

class HomeAdapter(
    private val list: List<HomeItems<Any>>,
    private val onClickTeamTodo: (TeamTodo) -> Unit,
    private val onClickPersonalTodo: (PersonalTodo) -> Unit,
    private var teamPendingTodosCount: Int = 0,
    private var personalPendingTodosCount: Int = 0
) :
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

            R.layout.list_personal_todos -> PersonalTodosHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_personal_todos,
                    parent,
                    false
                )
            )

            else -> TeamTodosHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.list_team_todos,
                    parent,
                    false
                )
            )


        }
    }

    fun setTeamCount(newTeamPendingTodosCount: Int) {
        teamPendingTodosCount = newTeamPendingTodosCount
        notifyDataSetChanged()
    }

    fun setPersonalCount(newPersonalPendingTodosCount: Int) {
        personalPendingTodosCount = newPersonalPendingTodosCount
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseHomeHolder, position: Int) {
        when (holder) {
            is StaticsTasksPersonHasDoneHolder -> holder.bind(list[position])
            is TodosSectionTitleHolder -> holder.bind(list[position])
            is PersonalTodosHolder -> holder.bind(list[position])
            is TeamTodosHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE -> R.layout.item_statistics_tasks_person_has_done
            HomeItemType.ITEM_TODOS_SECTION_TITLE -> R.layout.item_todos_section_title
            HomeItemType.LIST_PERSONAL_TASKS -> R.layout.list_personal_todos
            HomeItemType.LIST_TEAM_TASKS -> R.layout.item_team_todo

        }
    }

    abstract class BaseHomeHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item: HomeItems<Any>)
    }

    inner class StaticsTasksPersonHasDoneHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemStatisticsTasksPersonHasDoneBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val statistics = (item.data as Statistics)
            val list = ArrayList<PieEntry>()
            list.add(PieEntry(6f, "personal"))
            list.add(PieEntry(40f, "team"))
            val pieDataSet = PieDataSet(list, "list")
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
            pieDataSet.valueTextSize = 15f
            pieDataSet.valueTextColor = Color.BLACK
            val pieData = PieData(pieDataSet)
            binding.radarChartStatistic.data = pieData
            binding.radarChartStatistic.animateY(20000)

            binding.textViewPersonalResult.text =
                personalPendingTodosCount.toString()
            binding.textViewTeamResult.text =
                teamPendingTodosCount.toString()
//            binding.textViewCompletedTodoResult.text =
//                (item.data as GetAllPersonalTodosResponse).value.size.toString()
            binding.textViewPendingTodoResult.text =
                statistics.personal.value.size.toString()
        }

    }

    inner class TodosSectionTitleHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemTodosSectionTitleBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            binding.textViewTodoType.text = item.data as String
        }

    }

    inner class PersonalTodosHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ListPersonalTodosBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val adapter = GetAllPersonalTodosAdapter(
                item.data as GetAllPersonalTodosResponse,
                onClickPersonalTodo
            )
            binding.recyclerViewPersonalTodos.adapter = adapter
        }

    }

    inner class TeamTodosHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ListTeamTodosBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val statistics = item.data as Statistics
            val adapter = GetAllTeamTodosAdapter(statistics.team, onClickTeamTodo)
            binding.recyclerViewTeamTodos.adapter = adapter
        }

    }
}