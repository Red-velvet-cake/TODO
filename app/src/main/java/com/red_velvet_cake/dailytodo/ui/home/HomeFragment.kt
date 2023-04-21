package com.red_velvet_cake.dailytodo.ui.home


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.model.Statistics
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.databinding.DialogBinding
import com.red_velvet_cake.dailytodo.databinding.FragmentHomeBinding
import com.red_velvet_cake.dailytodo.ui.activity.AuthActivity
import com.red_velvet_cake.dailytodo.ui.base.BaseFragment
import com.red_velvet_cake.dailytodo.ui.create_todo.CreateTodoFragment
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeAdapter
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItemType
import com.red_velvet_cake.dailytodo.ui.home.adapter.HomeItems
import com.red_velvet_cake.dailytodo.ui.personal_todo.PersonalTodoFragment
import com.red_velvet_cake.dailytodo.ui.personal_todo_details.PersonalTodoDetailsFragment
import com.red_velvet_cake.dailytodo.ui.team_todo.TeamTodoFragment
import com.red_velvet_cake.dailytodo.ui.team_todo_details.TeamTodoDetailsFragment
import com.red_velvet_cake.dailytodo.ui.utils.showDialog
import com.red_velvet_cake.dailytodo.utils.navigateTo


class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeView {
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private var lists = mutableListOf<HomeItems<Any>>()
    private lateinit var adapter: HomeAdapter
    private val homePresenter = HomePresenter(this)
    private var personalTodos: HomeItems<Any> = HomeItems(
        GetAllPersonalTodosResponse(emptyList(), "", true),
        HomeItemType.LIST_PERSONAL_TASKS
    )
    private var teamTodos: HomeItems<Any> = HomeItems(
        GetAllTeamTodosResponse(emptyList(), "", true),
        HomeItemType.LIST_TEAM_TASKS
    )

    override fun setUp() {
        homePresenter.getAllTodos()
        val statistics: HomeItems<Any> = HomeItems(
            Statistics(),
            HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE
        )
        lists.add(
            statistics
        )
        lists.add(personalTodos)
        lists.add(teamTodos)

        adapter = HomeAdapter(
            lists,
            ::onClickTeamTodo,
            ::onClickPersonalTodo,
            ::onClickAllTeamTodos,
            ::onClickAllPersonalTodos
        )

        binding.recyclerViewHome.adapter = adapter

        binding.buttonAddTeamTodo.setOnClickListener {
            requireActivity().navigateTo(CreateTodoFragment())
        }

        requireActivity().supportFragmentManager.addOnBackStackChangedListener {
            if (parentFragmentManager.backStackEntryCount < 2) {
                homePresenter.getAllTodos()
            }
        }

        binding.textViewName.text = homePresenter.getUsername()
        binding.textViewAvatar.text = homePresenter.getUsername()[0].toString()

        binding.textViewAvatar.setOnClickListener {
            showDialog<DialogBinding>(requireActivity()) {
                it.buttonLogout.setOnClickListener {
                    homePresenter.logout()
                }
                it.textViewName.text = homePresenter.getUsername()
                it.textViewAvatar.text = homePresenter.getUsername()[0].toString()
            }
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
        binding.buttonTryAgain.setOnClickListener {
            homePresenter.getAllTodos()
        }
    }

    override fun showPersonalTodos(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        showHiddenSections()
        requireActivity().runOnUiThread {
            adapter.setPersonalCount(getAllPersonalTodosResponse.value.size)
            personalTodos.data = getAllPersonalTodosResponse
            adapter.notifyDataSetChanged()
        }
    }

    override fun showPendingTeamTodos(pendingTodo: Int) {
        requireActivity().runOnUiThread { adapter.setTeamPendingCount(pendingTodo) }
    }

    override fun showPendingPersonalTodos(pendingTodo: Int) {
        requireActivity().runOnUiThread { adapter.setPersonalPendingCount(pendingTodo) }
    }

    override fun showCompletedTeamTodos(completedTodo: Int) {
        requireActivity().runOnUiThread { adapter.setTeamCompleteCount(completedTodo) }
    }

    override fun showCompletedPersonalTodos(completedTodo: Int) {
        requireActivity().runOnUiThread { adapter.setPersonalCompleteCount(completedTodo) }
    }

    override fun showErrorOnPersonalTodoFailure(errorMessage: String) {
    }

    override fun showTeamTodos(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        showHiddenSections()
        requireActivity().runOnUiThread {
            adapter.setTeamCount(getAllTeamTodosResponse.value.size)
            teamTodos.data = getAllTeamTodosResponse
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
        requireActivity().navigateTo(PersonalTodoFragment.newInstance())
    }

    override fun navigateToLogin() {
        val toLoginIntent = Intent(requireActivity(), AuthActivity::class.java)
        requireActivity().startActivity(toLoginIntent)
        requireActivity().finish()
    }

    override fun showTryAgain() {
        requireActivity().runOnUiThread {
            binding.progressBarLoadState.visibility = View.GONE
            binding.recyclerViewHome.visibility = View.GONE
            binding.buttonAddTeamTodo.visibility = View.GONE
            binding.buttonTryAgain.visibility = View.VISIBLE
            binding.imageViewHasNoInternet.visibility = View.VISIBLE
        }
    }

    private fun showHiddenSections() {
        requireActivity().runOnUiThread {
            binding.recyclerViewHome.visibility = View.VISIBLE
            binding.buttonAddTeamTodo.visibility = View.VISIBLE
            binding.buttonTryAgain.visibility = View.GONE
            binding.imageViewHasNoInternet.visibility = View.GONE
        }
    }

    override fun showLoadStatus() {
        binding.progressBarLoadState.visibility = View.VISIBLE
    }

    override fun disableLoadStatus() {
        requireActivity().runOnUiThread {
            binding.progressBarLoadState.visibility = View.GONE
        }
    }
}