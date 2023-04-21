package com.red_velvet_cake.dailytodo.ui.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.red_velvet_cake.dailytodo.databinding.DialogBinding


fun <T : ViewBinding> Fragment.showDialog(
    activity: Activity,
    onCreateDialog: (T) -> Unit
) {
    val dialog = Dialog(activity)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    val binding = DialogBinding.inflate(layoutInflater)
    dialog.setContentView(binding.root)
    onCreateDialog(binding as T)
    dialog.show()
}