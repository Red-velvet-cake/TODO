package com.red_velvet_cake.dailytodo.ui.personal_todo

import com.red_velvet_cake.dailytodo.data.model.GetAllPersonalTodosResponse
import com.red_velvet_cake.dailytodo.data.model.PersonalTodo
import com.red_velvet_cake.dailytodo.data.remote.todo_service.TodoServiceImpl
import com.red_velvet_cake.dailytodo.data.remote.util.CustomException
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PersonalTodoPresenter(private val view: PersonalTodoView) {

    private val todoServiceImpl = TodoServiceImpl()
    private val compositeDisposable = CompositeDisposable()

    fun updateTeamTodoStatus(
        todoId: String,
        newTodoStatus: Int,
    ) {
        todoServiceImpl.updatePersonalTodoStatus(
            todoId,
            newTodoStatus,
            ::onUpdatePersonalTodoStatusFailure
        )
    }

    fun getAllTeamTodos() {
        view.showLoadStatus()
        todoServiceImpl.getAllPersonalTodos(
            ::onGetAllPersonalTodosSuccess,
            ::onGetAllTeamTodosFailure
        )
    }

    private fun onUpdatePersonalTodoStatusFailure(exception: Exception) {
        when (exception) {
            is CustomException.UnauthorizedUserException -> {
                view.navigateBack()
            }

            else -> {
                view.showTodoUpdateFailMessage(exception.message.toString())
            }
        }
    }

    private fun onGetAllPersonalTodosSuccess(getAllPersonalTodosResponse: GetAllPersonalTodosResponse) {
        view.disableLoadStatus()
        view.showTodoList(getAllPersonalTodosResponse.value.reversed())
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

    fun navigateToTodoDetails(personalTodo: PersonalTodo) {
        view.navigateToTodoDetails(personalTodo)
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