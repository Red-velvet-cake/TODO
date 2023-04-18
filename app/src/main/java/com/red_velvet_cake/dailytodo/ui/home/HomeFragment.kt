package com.red_velvet_cake.dailytodo.ui.home


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.red_velvet_cake.dailytodo.R
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentHomeBinding
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeAdapter
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItemType
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItems
import com.red_velvet_cake.dailytodo.ui.home.adapter.IHomeView
import java.io.IOException


class HomeFragment() : BaseFragment<FragmentHomeBinding>(), IHomeView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private var lists = mutableListOf<HomeItems<Any>>()
    private lateinit var adapter: HomeAdapter
    val homePresenter = HomePresenter(this)


    override fun setUp() {
        adapter = HomeAdapter(lists, ::onClickTeamTodo, ::onClickPersonalTodo)
        homePresenter.getAllTodos()
        binding.recyclerViewHome.adapter = adapter

        binding.buttonAddTeamTodo.setOnClickListener {
            val seetDialog =
                BottomSheetDialog(requireContext().applicationContext, R.style.bottomSheetStyle)

            val sheetView = LayoutInflater.from(requireContext().applicationContext)

        }

//        BottomSheetBehavior.from(binding.sheet).apply {
//            maxHeight=0
//            this.state=BottomSheetBehavior.STATE_HIDDEN
//        }

    }

    private fun onClickTeamTodo(teamTodo: TeamTodo) {

    }

    private fun onClickPersonalTodo(personalTodo: PersonalTodo) {
        homePresenter.navigateToPersonalTodoDetails(personalTodo)
    }

    override fun addCallBacks() {
    }

    override fun showPersonalTodos(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        requireActivity().runOnUiThread {
            adapter.setPersonalCount(getAllPersonalTodosResponse.value.size)
            lists.add(
                HomeItems(
                    Statistics(personal = getAllPersonalTodosResponse),
                    HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE
                )
            )
            lists.add(HomeItems("Personal Task", HomeItemType.ITEM_TODOS_SECTION_TITLE))
            lists.add(HomeItems(getAllPersonalTodosResponse, HomeItemType.LIST_PERSONAL_TASKS))
            adapter.notifyDataSetChanged()
        }
    }

    override fun showErrorOnPersonalTodoFailure(exception: IOException) {
        Log.i("mah", "personal ${exception.message}")
    }

    override fun showTeamTodos(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        lists.add(HomeItems("Team Task", HomeItemType.ITEM_TODOS_SECTION_TITLE))
        requireActivity().runOnUiThread {
            adapter.setTeamCount(getAllTeamTodosResponse.value.size)
            lists.add(
                HomeItems(
                    Statistics(team = getAllTeamTodosResponse),
                    HomeItemType.LIST_TEAM_TASKS
                )
            )
            adapter.notifyDataSetChanged()
        }


    }

    override fun showErrorOnTeamTodoFailure(exception: IOException) {
        Log.i("mah", "team ${exception.message}")
    }

    override fun navigateToTeamTodoDetails(teamTodo: TeamTodo) {
//        requireActivity().navigateTo()
    }

    override fun navigateToPersonalTodoDetails(personalTodo: PersonalTodo) {

    }
}

data class Statistics(
    val personal: GetAllPersonalTodosResponse = GetAllPersonalTodosResponse(emptyList(), "", true),
    val team: GetAllTeamTodosResponse = GetAllTeamTodosResponse(emptyList(), "", true)
)