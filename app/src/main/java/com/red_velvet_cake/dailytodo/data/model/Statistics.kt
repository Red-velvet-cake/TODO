package com.red_velvet_cake.dailytodo.data.model

data class Statistics(
    val personal: GetAllPersonalTodosResponse = GetAllPersonalTodosResponse(emptyList(), "", true),
    val team: GetAllTeamTodosResponse = GetAllTeamTodosResponse(emptyList(), "", true)
)