package com.red_velvet_cake.dailytodo.ui.home


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
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

    override fun setUp() {
        adapter = HomeAdapter(lists)
        val homePresenter = HomePresenter(this)
        homePresenter.getAllTodos()
        binding.recyclerViewHome.adapter = adapter

    }

    override fun addCallBacks() {
    }

    override fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        requireActivity().runOnUiThread {
            lists.add(
                HomeItems(
                    getAllPersonalTodosResponse,
                    HomeItemType.ITEM_STATISTICS_TASKS_HAS_DONE
                )
            )
            lists.add(HomeItems("Personal Task", HomeItemType.ITEM_TODOS_SECTION_TITLE))
            lists.add(HomeItems(getAllPersonalTodosResponse, HomeItemType.LIST_PERSONAL_TASKS))
            adapter.notifyDataSetChanged()
        }
    }

    override fun onGetAllPersonalTodosFailure(exception: IOException) {
        Log.i("mah", "personal ${exception.message}")
    }

    override fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        lists.add(HomeItems("Team Task", HomeItemType.ITEM_TODOS_SECTION_TITLE))
        requireActivity().runOnUiThread {
            lists.add(HomeItems(getAllTeamTodosResponse, HomeItemType.LIST_TEAM_TASKS))
            adapter.notifyDataSetChanged()
        }


    }

    override fun onGetAllTeamTodosFailure(exception: IOException) {
        Log.i("mah", "team ${exception.message}")
    }
}