package com.red_velvet_cake.dailytodo.domain

import android.icu.text.CaseMap.Title
import okhttp3.Response
import java.io.IOException

interface TodoService {
    fun createTeamTodo(
        title: String,
        description:String,
        assignee:String,
        onCreateTeamTodoSuccess: (Response) -> Unit,
        onCreateTeamTodoFailure: (IOException) -> Unit
        )
}