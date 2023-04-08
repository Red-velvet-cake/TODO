package com.red_velvet_cake.dailytodo.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T : Any, VB : ViewBinding> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<VB>>() {

    private var items: List<T> = listOf()
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    abstract fun bindItem(binding: VB, item: T)

    abstract fun <T> areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int,
        newItems: List<T>
    ): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = bindingInflater(inflater, parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bindItem(holder.binding, items[position])
    }

    override fun getItemCount(): Int = items.size
    class BaseViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    fun submitList(newItems: List<T>) {
        val diffResult = DiffUtil.calculateDiff(AppDiffUtil(items, newItems, ::areItemsTheSame))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    fun getOldItems() = items

    private class AppDiffUtil<T>(
        private val oldList: List<T>,
        private val newList: List<T>,
        val function: (Int, Int, List<T>) -> Boolean
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = function(oldItemPosition, newItemPosition, newList)

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]

    }
}
