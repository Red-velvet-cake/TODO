package com.red_velvet_cake.dailytodo.ui.home


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

    override fun setUp() {
        val homePresenter = HomePresenter(this)
        homePresenter.getAllTodos()

        val adapter = HomeAdapter(lists)
        binding.recyclerViewHome.adapter = adapter

    }

    override fun addCallBacks() {

    }

    override fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        lists.add(HomeItems(getAllPersonalTodosResponse, HomeItemType.LIST_PERSONAL_TASKS))
    }

    override fun onGetAllPersonalTodosFailure(e: IOException) {
    }

    override fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        lists.add(HomeItems(getAllTeamTodosResponse, HomeItemType.LIST_PERSONAL_TASKS))
    }

    override fun onGetAllTeamTodosFailure(exception: IOException) {
        TODO("Not yet implemented")
    }
}