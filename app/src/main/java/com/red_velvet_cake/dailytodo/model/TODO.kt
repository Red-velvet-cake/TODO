package com.red_velvet_cake.dailytodo.model

data class TODO(
    val title: String,
    val description: String,
    val assignee: String? = null
)