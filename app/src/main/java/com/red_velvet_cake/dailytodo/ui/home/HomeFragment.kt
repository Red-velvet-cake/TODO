package com.red_velvet_cake.dailytodo.ui.home


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.Statistics
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.FragmentHomeBinding
import com.red_velvet_cake.dailytodo.ui.activity.AuthActivity
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.createTodo.CreateTodoFragment
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeAdapter
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItemType
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItems
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeView
import com.red_velvet_cake.dailytodo.ui.personal_todo.PersonalTodoViewFragment
import com.red_velvet_cake.dailytodo.ui.personal_todo_details.PersonalTodoDetailsFragment
import com.red_velvet_cake.dailytodo.ui.team_todo.TeamTodoFragment
import com.red_velvet_cake.dailytodo.ui.team_todo_details.TeamTodoDetailsFragment
import com.red_velvet_cake.dailytodo.utils.navigateTo


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private var lists = mutableListOf<HomeItems<Any>>()
    private lateinit var adapter: HomeAdapter
    private val homePresenter = HomePresenter(this)


    override fun setUp() {
        adapter = HomeAdapter(
            lists,
            ::onClickTeamTodo,
            ::onClickPersonalTodo,
            ::onClickAllTeamTodos,
            ::onClickAllPersonalTodos
        )
        homePresenter.getAllTodos()
        binding.recyclerViewHome.adapter = adapter

        binding.buttonAddTeamTodo.setOnClickListener {
            requireActivity().navigateTo(CreateTodoFragment())
        }
    }

    private fun onClickTeamTodo(teamTodo: TeamTodo) {
        homePresenter.navigateToTeamTodoDetails(teamTodo)
    }

    private fun onClickPersonalTodo(personalTodo: PersonalTodo) {
        homePresenter.navigateToPersonalTodoDetails(personalTodo)
    }

    private fun onClickAllTeamTodos() {
        homePresenter.navigateToAllTeamTodos()
    }

    private fun onClickAllPersonalTodos() {
        homePresenter.navigateToAllPersonalTodos()
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
            lists.add(HomeItems(getAllPersonalTodosResponse, HomeItemType.LIST_PERSONAL_TASKS))
            adapter.notifyDataSetChanged()
        }
    }

    override fun showErrorOnPersonalTodoFailure(errorMessage: String) {
    }

    override fun showTeamTodos(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
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

    override fun showErrorOnTeamTodoFailure(errorMessage: String) {

    }

    override fun navigateToTeamTodoDetails(teamTodo: TeamTodo) {
        requireActivity().navigateTo(
            TeamTodoDetailsFragment.newInstance(teamTodo),
        )
    }

    override fun navigateToPersonalTodoDetails(personalTodo: PersonalTodo) {
        requireActivity().navigateTo(
            PersonalTodoDetailsFragment.newInstance(personalTodo),
        )
    }

    override fun navigateToAllTeamTodos() {
        requireActivity().navigateTo(TeamTodoFragment.newInstance())
    }

    override fun navigateToAllPersonalTodos() {
        requireActivity().navigateTo(PersonalTodoViewFragment.newInstance())
    }

    override fun navigateToLogin() {
        val toLoginIntent = Intent(requireActivity(), AuthActivity::class.java)
        requireActivity().startActivity(toLoginIntent)
        requireActivity().finish()
    }
}