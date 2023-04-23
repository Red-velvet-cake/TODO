package com.red_velvet_cake.dailytodo.ui.team_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllTeamTodosResponse
import com.red_velvet_cake.dailytodo.data.model.TeamTodo
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TeamTodoPresenter(private val view: TeamTodoView) {

    private val todoServiceImpl = TodoServiceImpl()
    private val compositeDisposable = CompositeDisposable()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updateTeamTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdateTeamTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        view.showLoadStatus()
        todoServiceImpl.getAllTeamTodos(
            ::onGetAllTeamTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdateTeamTodoStatusFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTodoUpdateFailMessage(exception.message.toString())
            }
        }
    }

    private fun onGetAllTeamTodosSuccess(getAllTeamTodosResponse: GetAllTeamTodosResponse) {
        view.showTodoList(getAllTeamTodosResponse.value.reversed())
    }

    private fun onGetAllTeamTodosFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showLoadTodosFailed()
            }
        }
    }

    fun navigateToTodoDetails(todo: TeamTodo) {
        view.navigateToTodoDetails(todo)
    }

    fun searchTodos(searchQuery: String) {
        compositeDisposable.clear()

        if (searchQuery.isBlank()) {
            getAllTeamTodos()
        } else {
            val disposable = Observable.just(searchQuery)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .map { query ->
                    view.getDisplayedTodoList().filter {
                        it.title.contains(query, ignoreCase = true) ||
                                it.description.contains(query, ignoreCase = true)
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { filteredList ->
                    view.showTodoList(filteredList)
                }
            compositeDisposable.add(disposable)
        }
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}