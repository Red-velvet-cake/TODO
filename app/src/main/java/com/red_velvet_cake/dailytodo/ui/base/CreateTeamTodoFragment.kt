package com.red_velvet_cake.dailytodo.ui

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import com.red_velvet_cake.dailytodo.R

class CreateTeamTodoFragment : Activity() {
    var bottomsheet: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_create_team_todo)
        bottomsheet!!.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.fragment_create_team_todo)
    }
}