package com.red_velvet_cake.dailytodo.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.red_velvet_cake.dailytodo.R

enum class NavigationState {
    ADD,
    REPLACE,
    REMOVE,
}

fun FragmentActivity.navigateTo(
    to: Fragment,
    containerId: Int = R.id.fragment_container_view_home
) {
    changeNavigation(this, NavigationState.ADD, to, containerId)
}

fun FragmentActivity.navigateExclusive(
    to: Fragment,
    containerId: Int = R.id.fragment_container_view_home
) {
    changeNavigation(this, NavigationState.REPLACE, to, containerId)
}

fun FragmentActivity.navigateBack(containerId: Int = R.id.fragment_container_view_home) {
    changeNavigation(this, NavigationState.REMOVE, null, containerId)
}

private fun changeNavigation(
    activity: FragmentActivity,
    state: NavigationState,
    to: Fragment?,
    containerId: Int
) {
    val fragmentManager = activity.supportFragmentManager
    val transaction = fragmentManager.beginTransaction()

    when (state) {
        NavigationState.ADD -> {
            transaction.add(containerId, to!!)
            transaction.addToBackStack("fragment")
        }

        NavigationState.REMOVE -> {
            fragmentManager.popBackStack()
        }

        NavigationState.REPLACE -> {
            transaction.replace(containerId, to!!)
        }
    }
    transaction.commit()
}