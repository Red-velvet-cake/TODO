package com.red_velvet_cake.dailytodo.ui.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.Statistics
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.ItemSectionPersonalTodosBinding
import com.red_velvet_cake.dailytodo.databinding.ItemSectionTeamTodosBinding
import com.red_velvet_cake.dailytodo.databinding.ItemStatisticsTasksPersonHasDoneBinding

class HomeAdapter(
    private val list: List<HomeItems<Any>>,
    private val onClickTeamTodoItem: (TeamTodo) -> Unit,
    private val onClickPersonalTodoItem: (PersonalTodo) -> Unit,
    private val onClickAllTeamTodos: () -> Unit,
    private val onClickAllPersonalTodos: () -> Unit,
    private var teamPendingTodosCount: Int = 0,
    private var personalPendingTodosCount: Int = 0,
    private var pendingTeamTodosCount: Int = 0,
    private var pendingPersonalTodosCount: Int = 0,
    private var completedTeamTodosCount: Int = 0,
    private var completedPersonalTodosCount: Int = 0

) : RecyclerView.Adapter<HomeAdapter.BaseHomeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHomeHolder {
        return when (viewType) {
            R.layout.item_statistics_tasks_person_has_done -> StaticsTasksPersonHasDoneHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_statistics_tasks_person_has_done,
                    parent,
                    false
                )
            )

            R.layout.item_section_personal_todos -> PersonalTodosHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_section_personal_todos,
                    parent,
                    false
                )
            )

            else -> TeamTodosHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_section_team_todos,
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
    fun setTeamPendingCount(pendingTodo: Int) {
        pendingTeamTodosCount = pendingTodo
        notifyDataSetChanged()
    }
    fun setPersonalPendingCount(pendingTodo: Int) {
        pendingPersonalTodosCount = pendingTodo
        notifyDataSetChanged()
    }
    fun setTeamCompleteCount(pendingTodo: Int) {
        completedPersonalTodosCount = pendingTodo
        notifyDataSetChanged()
    }
    fun setPersonalCompleteCount(pendingTodo: Int) {
        completedTeamTodosCount = pendingTodo
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BaseHomeHolder, position: Int) {
        when (holder) {
            is StaticsTasksPersonHasDoneHolder -> holder.bind(list[position])
            is PersonalTodosHolder -> holder.bind(list[position])
            is TeamTodosHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].type) {
            HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE -> R.layout.item_statistics_tasks_person_has_done
            HomeItemType.LIST_PERSONAL_TASKS -> R.layout.item_section_personal_todos
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

            val statisticsList = ArrayList<PieEntry>()
            val colors = ArrayList<Int>()
            colors.add(Color.rgb(248, 247, 255))
            colors.add(Color.rgb(176, 160, 255))
            statisticsList.add(PieEntry(personalPendingTodosCount.toFloat(), ""))
            statisticsList.add(PieEntry(teamPendingTodosCount.toFloat(), ""))
            val pieDataSet = PieDataSet(statisticsList, "")
            pieDataSet.colors = colors
            val pieData = PieData(pieDataSet)
            pieData.setDrawValues(false)


            binding.pieChartStatistic.apply {
                data = pieData
                animateY(2000)
                description.isEnabled = false
                legend.isEnabled = false
            }


            binding.textViewPersonalResult.text =
                personalPendingTodosCount.toString()
            binding.textViewTeamResult.text =
                teamPendingTodosCount.toString()
//            binding.textViewCompletedTodoResult.text =
//                (item.data as GetAllPersonalTodosResponse).value.size.toString()
            binding.textViewPendingTodoResult.text =
                pendingTeamTodosCount.plus(pendingPersonalTodosCount).toString()
            binding.textViewCompletedTodoResult.text =
                completedTeamTodosCount.plus(completedPersonalTodosCount).toString()
        }

    }

    inner class PersonalTodosHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemSectionPersonalTodosBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val adapter = GetAllPersonalTodosAdapter(
                item.data as GetAllPersonalTodosResponse,
                onClickPersonalTodoItem
            )
            binding.recyclerViewPersonalTodos.adapter = adapter
            binding.textViewShowShowAll.setOnClickListener {
                onClickAllPersonalTodos()
            }
        }
    }

    inner class TeamTodosHolder(viewItem: View) : BaseHomeHolder(viewItem) {
        private val binding = ItemSectionTeamTodosBinding.bind(viewItem)
        override fun bind(item: HomeItems<Any>) {
            val statistics = item.data as Statistics
            val adapter = GetAllTeamTodosAdapter(statistics.team, onClickTeamTodoItem)
            binding.recyclerViewTeamTodos.adapter = adapter
            binding.textViewShowShowAll.setOnClickListener {
                onClickAllTeamTodos()
            }
        }
    }
}