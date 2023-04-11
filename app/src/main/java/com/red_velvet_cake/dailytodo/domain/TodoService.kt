package com.red_velvet_cake.dailytodo.domain

import android.icu.text.CaseMap.Title

interface TodoService {
    fun creatTeamTodo(title: String,description:String,assignee:String)
}